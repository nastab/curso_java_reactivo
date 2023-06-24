package com.example.demo;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DojoStreamTest {

    @Test
    void converterData(){
        List<Player> list = CsvUtilFile.getPlayers();
        assert list.size() == 18207;
    }

    @Test
    void jugadoresMayoresA35(){
        List<Player> list = CsvUtilFile.getPlayers();
        Set<Player> result = list.stream()
                .filter(jugador -> jugador.getAge() > 35)
                .collect(Collectors.toSet());
        result.forEach(System.out::println);
    }

    @Test
    void jugadoresMayoresA35SegunClub(){
        List<Player> list = CsvUtilFile.getPlayers();
        Map<String, List<Player>> result = list.stream()
                .filter(player -> player.getAge() > 35)
                .collect(Collectors.groupingBy(Player::getClub));
        result.forEach((s, players) -> {
            System.out.println(s);
            players.forEach(System.out::println);
        });
    }

    @Test
    void mejorJugadorConNacionalidadFrancia(){
        List<Player> list = CsvUtilFile.getPlayers();
        list.stream().filter(p -> p.getNational().equals("France"))
                .reduce((j1, j2)-> j1.getWinners() > j2.getWinners() ? j1 : j2)
                .ifPresent(System.out::println);
    }


    @Test
    void clubsAgrupadosPorNacionalidad(){
        List<Player> list = CsvUtilFile.getPlayers();
        Function<Player, List<Object>> compositeKey = r -> Arrays.<Object>asList(r.getNational(), r.getClub());
        Map<Object, List<Player>> map =
                list.stream().collect(Collectors.groupingBy(compositeKey, Collectors.toList()));
        map.forEach((o, players) -> {
            System.out.println(o);
            players.forEach(System.out::println);
        });
    }

    @Test
    void clubConElMejorJugador(){
        List<Player> list = CsvUtilFile.getPlayers();
        list.stream().reduce((j1, j2)-> j1.getWinners() > j2.getWinners() ? j1 : j2)
                .ifPresent(p -> System.out.println("Club con el mejor jugador: " + p.getClub()));
    }

    @Test
    void ElMejorJugador(){
        List<Player> list = CsvUtilFile.getPlayers();
        list.stream().reduce((j1, j2)-> j1.getWinners() > j2.getWinners() ? j1 : j2)
                .ifPresent(p -> System.out.println("El mejor jugador: " + p.getName() + "\n" + p));
    }

    @Test
    void mejorJugadorSegunNacionalidad(){
    List<Player> list = CsvUtilFile.getPlayers();
        list.sort(Comparator.comparing(Player::getNational));
        Map<String, Optional<Player>> result = list.stream()
                .collect(Collectors.groupingBy(Player::getNational, LinkedHashMap::new, Collectors.reducing((j1, j2)-> j1.getWinners() > j2.getWinners() ? j1 : j2)));
        result.forEach((s, player) -> {
            System.out.println("\n" + s);
            player.ifPresent(System.out::println);
        });
    }


}
