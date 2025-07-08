package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Scanner;

public class LogQueryGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("[필드명 입력] : ");
        String field = scanner.nextLine();

        System.out.print("[검색어 입력] : ");
        String keyword = scanner.nextLine();

        System.out.print("[시작일 입력] (예: 2025-01-01): ");
        String startDate = scanner.nextLine();

        System.out.print("[종료일 입력] (예: 2025-02-01): ");
        String endDate = scanner.nextLine();

        JsonObject match = new JsonObject();
        JsonObject matchField = new JsonObject();
        matchField.addProperty(field, keyword);
        match.add("match", matchField);

        JsonObject range = new JsonObject();
        JsonObject timestamp = new JsonObject();
        timestamp.addProperty("gte", startDate);
        timestamp.addProperty("lte", endDate);
        range.add("@timestamp", timestamp);

        JsonObject rangeWrapper = new JsonObject();
        rangeWrapper.add("range", range);

        JsonArray mustArray = new JsonArray();
        mustArray.add(match);
        mustArray.add(rangeWrapper);

        JsonObject bool = new JsonObject();
        bool.add("must", mustArray);

        JsonObject query = new JsonObject();
        query.add("bool", bool);

        JsonObject root = new JsonObject();
        root.add("query", query);

        System.out.println("\n📦 생성된 Elasticsearch 쿼리:\n");
        System.out.println(root.toString());
    }
}