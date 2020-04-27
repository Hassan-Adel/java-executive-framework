package com.tutorial.streams;

import com.tutorial.models.Genre;
import com.tutorial.models.Movie;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void show(){
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );

        var count = movies.stream().filter(i -> i.getLikes()>10).count();
        System.out.println(count);
    }

    public static void MapElements(){
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );
        var movieTitles = movies.stream().map(m ->m.getName());
        movieTitles.forEach(mt -> System.out.println(mt));

        //flat map
        var streamOfLists= Stream.of(List.of(1,2,3), List.of(4,5,6));
        //flatten to list of ints
        //Stream<List<x>> -> Stream<x>
        streamOfLists.flatMap(list -> list.stream())
                .forEach(n -> System.out.println(n));
    }

    public static void FilteringStreams(){
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 15),
                new Movie("c", 20)
        );
        Predicate<Movie> isPopular  = movie -> movie.getLikes()>10;
        //1
        movies.stream().filter(m -> m.getLikes()>10)
                .forEach(l -> System.out.println(l));
        //2
        movies.stream().filter(isPopular)
                .forEach(l -> System.out.println(l));
    }

    public static void SlicingStreams(){
        List<Movie> movies = List.of(
                new Movie("a", 10),
                new Movie("b", 30),
                new Movie("c", 20)
        );
        //limit
        movies.stream()
                .limit(2)
                .forEach(m  -> System.out.println(m.getName()));

        //skip
        //1000 movies
        // 10 movies per page
        //3rd page = skip(20)
        //limit(10)
        //pagination( skip((page - 1) x pageSize))
        movies.stream()
                .skip(2)
                .forEach(m  -> System.out.println(m.getName()));

        //takeWhile : returns the moment the predicate returns false (will only return movies a)
        movies.stream()
                .takeWhile(m -> m.getLikes()<30)
                .forEach(m  -> System.out.println(m.getName()));

        //dropWhile (will return movie a & b)
        movies.stream()
                .dropWhile(m -> m.getLikes()<30)
                .forEach(m  -> System.out.println(m.getName()));
    }

    public static void CreateStreams(){
        Collection<Integer> test = List.of(1,23,63,7,4,2);
        var stream = test.stream();

        int[] arr = {1,3,52,6};
        var stream2 = Arrays.stream(arr);
        stream2.forEach(n -> System.out.println(n));

        Stream.of(1,5,34,768,3);

        var infiniteStream =  Stream.generate(()->Math.random());
        infiniteStream.limit(10).forEach(n-> System.out.println(n));

        var infiniteStream2 = Stream.iterate(1, n->n+2);
        infiniteStream2.limit(3).forEach(n-> System.out.println(n));
    }

    public static void SortingStreams(){
        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 30),
                new Movie("c", 20)
        );
        //var sortedMovies  = movies.stream().sorted((a, b) -> a.getName().compareTo(b.getName()));
        movies.stream().sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(m -> System.out.println(m.getName()));

        //another way
        movies.stream().sorted(Comparator.comparing(m ->m.getName()))
                .forEach(m -> System.out.println(m.getName()));

        //easier way : desc
        movies.stream().sorted(Comparator.comparing(Movie::getName).reversed())
                .forEach(m -> System.out.println(m.getName()));

    }

    public static void GetUniqueValuesFromStream() {
        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 30),
                new Movie("a", 30),
                new Movie("c", 20),
                new Movie("c", 20),
                new Movie("c", 20)
        );
        movies.stream()
                .map(m -> m.getLikes())
                .distinct() //unique
                .forEach(like -> System.out.println(like));
    }


    public static void PeekElementsInStream() {
        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 30),
                new Movie("d", 30),
                new Movie("c", 20),
                new Movie("e", 20),
                new Movie("f", 20)
        );
        movies.stream()
                .filter(m -> m.getLikes() > 10)
                .peek(m -> System.out.println("filtered: " + m))
                .map(movie -> movie.getName())
                .peek(n -> System.out.println("mapped: " + n))
                .forEach(n -> System.out.println(n));
    }

    public static void StreamReducers() {

        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 30),
                new Movie("d", 30),
                new Movie("c", 60),
                new Movie("e", 20),
                new Movie("f", 20)
        );

        var count = movies.stream().count();
        boolean allMatch = movies.stream().allMatch(m -> m.getLikes()>20); //false
        boolean anyMatch = movies.stream().anyMatch(m -> m.getLikes()> 20); //true
        boolean noneMatch = movies.stream().noneMatch(m -> m.getLikes()> 20); //false
        Movie movie = movies.stream().findFirst().get();
        var findAny = movies.stream().findAny();
        var movieWithMaxLikes = movies.stream().max(Comparator.comparing(Movie::getLikes)).get();
        var movieWithMinLikes = movies.stream().min(Comparator.comparing(Movie::getLikes)).get();
    }

    public static void GeneralStreamReducers() {

        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 30),
                new Movie("c", 60)
        );
        //get likes sum (reduce)
        //[10,30,60]
        //[40, 60]
        //[100]
        Optional<Integer> sum = movies.stream()
                .map(m -> m.getLikes())
                .reduce((a,b)-> a+b);
        //return 0 in case of null (avoid exception)
        System.out.println(sum.orElse(0));

        //same result using method reference
        Optional<Integer> sumMethodReference = movies.stream()
                .map(m -> m.getLikes())
                .reduce(Integer::sum);
        //return 0 in case of null (avoid exception)
        System.out.println(sumMethodReference.orElse(0));


        //can also avoid null exception by using the identity parameter
        Integer intSum = movies.stream()
                .map(m -> m.getLikes())
                .reduce(0, Integer::sum);
        System.out.println(intSum);
    }

    public static void Collectors() {
        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 30),
                new Movie("a", 70),
                new Movie("c", 60)
        );

        var collectLikes = movies.stream()
                .map(m -> m.getLikes())
                .collect(Collectors.toList());


        var filteredMovies = movies.stream()
                .filter(m -> m.getLikes()>50)
                .collect(Collectors.toSet());

        //key(title) val(likes)
        var moviesMap = movies.stream()
                .filter(m->m.getLikes()> 50)
                .collect(Collectors.toMap(m -> m.getName(), m -> m.getLikes()));

        //key(title) val(movie)
        var moviesMap1 = movies.stream()
                .filter(m->m.getLikes()> 50)
                .collect(Collectors.toMap(m -> m.getName(), m -> m));

        //key(title) val(movie) .. same as above to avoid (m -> m)
        var moviesMap2 = movies.stream()
                .filter(m->m.getLikes()> 50)
                .collect(Collectors.toMap(m -> m.getName(), Function.identity()));

        System.out.println(moviesMap);

        //collect total likes (similar to reduce)
        var likesCollector = movies.stream()
                .filter(m -> m.getLikes()>10 )
                .collect(Collectors.summingInt(m -> m.getLikes()));

        System.out.println(likesCollector);

        //collect statistics on movie likes
        var collectLikesStats = movies.stream()
                .filter(m -> m.getLikes()>50 )
                .collect(Collectors.summarizingInt(m -> m.getLikes()));

        System.out.println(collectLikesStats);

        //join movie titles with "," delimiter
        var collectLikesDelimited = movies.stream()
                .filter(m -> m.getLikes()>20)
                .map(m -> m.getName())
                .collect(Collectors.joining(","));

        System.out.println(collectLikesDelimited);

    }

    public static void GroupingElements() {

        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 30, Genre.COMEDY),
                new Movie("a", 70,Genre.ACTION),
                new Movie("c", 60, Genre.ROMANCE)
        );

        //Map<Genre, List<Movie>>
        var groupedByGenre = movies.stream()
                .collect(Collectors.groupingBy(m -> m.getGenre()));

        System.out.println(groupedByGenre);

        //Map<Genre, Set<Movie>>
        var groupedByGenreIntoSet = movies.stream()
                .collect(Collectors.groupingBy(m -> m.getGenre(), Collectors.toSet()));

        System.out.println(groupedByGenreIntoSet);

        //Map<Genre, int> number of movies in a genre
        var groupedByGenreCounting = movies.stream()
                .collect(Collectors.groupingBy(m -> m.getGenre(), Collectors.counting()));

        System.out.println(groupedByGenreCounting);

        //Map<Genre, string> names of movies delimited by ","
        var groupedByGenreDelimited = movies.stream()
                .collect(Collectors.groupingBy(m -> m.getGenre(),Collectors.mapping(m-> m.getName(), Collectors.joining(","))));
        //same
        groupedByGenreDelimited = movies.stream()
                .collect(Collectors.groupingBy(Movie::getGenre,Collectors.mapping(Movie::getName, Collectors.joining(","))));

        System.out.println(groupedByGenreDelimited);
    }

    public static void PartitioningElements() {
        List<Movie> movies = List.of(
                new Movie("b", 10),
                new Movie("a", 30, Genre.COMEDY),
                new Movie("a", 70,Genre.ACTION),
                new Movie("c", 60, Genre.ROMANCE)
        );

        //partition movies with like > 30 and < 30
        var partitionMovieLikes =  movies.stream()
                .collect(Collectors.partitioningBy(movie -> movie.getLikes()>30));

        System.out.println(partitionMovieLikes);

        //can do same functions on the result set as the group by examples above
        var partitionMovieLikesDelimited =  movies.stream()
                .collect(Collectors.partitioningBy(movie -> movie.getLikes()>30, Collectors.mapping(Movie::getName, Collectors.joining(","))));

        System.out.println(partitionMovieLikesDelimited);
    }

    public static void PrimitiveTypeStreams() {
        //has the same stream methods + some additional useful methods:

        //exclusive (1,2,3,4)
        IntStream.range(1,5).forEach(n -> System.out.print(n));

        System.out.println();

        //inclusive (1,2,3,4,5)
        IntStream.rangeClosed(1,5).forEach(System.out::print);
    }
}
