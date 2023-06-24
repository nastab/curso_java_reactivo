package com.example.demo;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

public class DojoReactiveTest {

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35() {
        List<Player> list = CsvUtilFile.getPlayers();
        Mono<List<Player>> mono = Mono.just(list);
        Flux<Player> observable = Flux.fromIterable(list);

        observable.filter(jugador -> jugador.getAge() > 35)
                .subscribe(System.out::println);
    }


@Test
    void jugadoresMayoresA35SegunClub(){
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable.filter(jugador -> jugador.getAge() > 35)
                .collect(Collectors.groupingBy(Player::getClub))
                .subscribe(System.out::println);
    }


    @Test
    void mejorJugadorConNacionalidadFrancia(){
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable.filter(jugador -> jugador.getNational().equals("France"))
                .reduce((j1, j2)-> j1.getWinners() > j2.getWinners() ? j1 : j2)
                .subscribe(System.out::println);
    }

    @Test
    void clubsAgrupadosPorNacionalidad(){
        List<Player> readCsv = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(readCsv);

        observable.collect(Collectors.groupingBy(Player::getClub, Collectors.groupingBy(Player::getNational)))
            .subscribe(map -> {
                map.forEach((club, players) -> {
                    System.out.println("\nclub: " + club + ": ");
                    players.forEach((national, players2) -> {
                        System.out.println("\n" + national + ": ");
                        players2.forEach(System.out::println);
                    });
                });
            });
    }

    @Test
    void clubConElMejorJugador(){
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable.reduce((j1, j2)-> j1.getWinners() > j2.getWinners() ? j1 : j2)
                .subscribe(System.out::println);
    }

    @Test
    void clubConElMejorJugador2() {
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable
                .reduce((p1, p2) -> ((p1.getWinners()/p1.getGames())>=(p2.getWinners()/p2.getGames())?p1:p2))
                .subscribe(player -> System.out.println(player.getClub() + "\n " + player));
    }

    @Test
    void ElMejorJugador() {
        List<Player> list = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(list);

        observable
                .reduce((p1, p2) -> ((p1.getWinners()/p1.getGames())>=(p2.getWinners()/p2.getGames())?p1:p2))
                .subscribe(player -> System.out.println(player));
    }

    @Test
    void mejorJugadorSegunNacionalidad(){
        List<Player> readCsv = CsvUtilFile.getPlayers();
        Flux<Player> observable = Flux.fromIterable(readCsv);

        observable
                .groupBy(Player::getNational)
                .flatMap(groupedFlux -> groupedFlux
                        .collectList()
                        .map(list -> {
                            Player best = list.stream().reduce((p1, p2)->((p1.getWinners()/p1.getGames())>=(p2.getWinners()/p2.getGames())?p1:p2)).get();
                            Map<String, Player> map = new HashMap<>();
                            map.put(groupedFlux.key(), best);
                            return map;
                        }))
                .subscribe(map -> {
                    map.forEach((k, v) -> {
                        System.out.println("\n");
                        System.out.println(k + ": ");
                        System.out.println(v);
                    });
                });
    }



}
