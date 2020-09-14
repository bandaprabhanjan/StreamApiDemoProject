package com.prabhanjan.stream.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StreamApiDemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamApiDemoProjectApplication.class, args);
		List<ProductBean> productList = Arrays.asList(new ProductBean(10, "Tomato"), new ProductBean(15, "Lemon"),
				new ProductBean(120, "Brinjal"), new ProductBean(45, "Potato"));

		System.out.println("------1. Creating Parallel Stream from collection-------");
		Stream<ProductBean> streamOfCollection = productList.parallelStream();
		boolean parallel = streamOfCollection.isParallel();
		System.out.println(parallel);
		boolean bigPrice = streamOfCollection.map(price -> price.getPrice() * 12).anyMatch(p -> p > 200);
		System.out.println(bigPrice);

		System.out.println("------2. Creating Parallel Stream from IntStream LongStream Double Stream-------");
		IntStream intStreamParallel = IntStream.range(0, 150).parallel();
		boolean parallel2 = intStreamParallel.isParallel();
		System.out.println(parallel2);

		System.out.println("------3. Convert Parallel Stream from Sequential Mode-------");
		IntStream intStreamSequential = intStreamParallel.sequential();
		boolean parallel3 = intStreamSequential.isParallel();
		System.out.println(parallel3);

		System.out.println("----------Converting Stream to Collection----------------");
		List<String> collectProcucts = productList.stream().map(product -> product.getName().toUpperCase())
				.collect(Collectors.toList());
		collectProcucts.forEach(System.out::println);

		System.out.println("---------------------Reduce to String------------------");
		String collectWithComma = productList.stream().map(ProductBean::getName)
				.collect(Collectors.joining(",", "[", "]"));
		System.out.println(collectWithComma);

		System.out.println("--------------------Average Price------------------------");
		Double collectAvg = productList.stream().collect(Collectors.averagingInt(ProductBean::getPrice));
		System.out.println(collectAvg);

		System.out.println("---------------------Summarizing Statistics-----------------");
		IntSummaryStatistics collect = productList.stream().collect(Collectors.summarizingInt(ProductBean::getPrice));
		System.out.println(collect);

		System.out.println("-------Divide streams elements into groups according to some predicate");
		Map<Boolean, List<ProductBean>> collectGroups = productList.stream()
				.collect(Collectors.partitioningBy(product -> product.getPrice() > 65));
		System.out.println("----2nd Print----");
		System.out.println(collectGroups);
		collectGroups.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(v);
		});
		
		System.out.println("-------pushing the collector to perform additonal transformation---------");
		Set<ProductBean> collectPush = productList.stream()
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
		collectPush.forEach(System.out::println);

		System.out.println("---------------------Group By Products-----------------");
		Map<Integer, List<ProductBean>> groupByPrice = productList.stream()
				.collect(Collectors.groupingBy(ProductBean::getPrice));
		groupByPrice.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(v);
		});
	}

}
