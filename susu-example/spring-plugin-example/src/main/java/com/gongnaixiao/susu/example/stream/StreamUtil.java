package com.gongnaixiao.susu.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtil {

	public static void main(String[] args) {
		Stream<String> a = Stream.of("a", "b", "c");
		a.forEach(s -> System.out.println(s));

		IntStream build = IntStream.builder().add(1).add(2).add(3).build();
		build.forEach(System.out::println);

		ArrayList<Object> objects = new ArrayList<>();
		objects.stream();

		Random random = new Random();
		Stream<Integer> generate = Stream.generate(random::nextInt).limit(10);
		generate.forEach(System.out::println);

		Stream<String> hello = Stream.of("hello", "java", "stream", "xxx-ggg");
		List<String[]> collect = hello.map(world -> world.split("-")).collect(Collectors.toList());

		Stream<String> hello1 = Stream.of("hello", "java", "stream", "xxx-ggg");
		List<String> collect1 = hello1.map(word -> word.split("-"))
				.flatMap(Arrays::stream)
				.collect(Collectors.toList());

		System.out.println("\n");
	}

}
