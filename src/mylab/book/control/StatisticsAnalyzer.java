package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StatisticsAnalyzer {

    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Double> totalPrice = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Publication p : publications) {
            String type = getPublicationType(p);
            totalPrice.put(type, totalPrice.getOrDefault(type, 0.0) + p.getPrice());
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> averages = new HashMap<>();
        for (String type : totalPrice.keySet())
            averages.put(type, totalPrice.get(type) / count.get(type));
        return averages;
    }

    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> count = new HashMap<>();
        for (Publication p : publications) {
            String type = getPublicationType(p);
            count.put(type, count.getOrDefault(type, 0) + 1);
        }
        Map<String, Double> distribution = new HashMap<>();
        for (String type : count.keySet())
            distribution.put(type, (double) count.get(type) / publications.length * 100);
        return distribution;
    }

    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int count = 0;
        for (Publication p : publications)
            if (p.getPublishDate().substring(0, 4).equals(year)) count++;
        return (double) count / publications.length * 100;
    }

    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) return "소설";
        else if (pub instanceof Magazine) return "잡지";
        else if (pub instanceof ReferenceBook) return "참고서";
        return "기타";
    }

    public void printStatistics(Publication[] publications) {
        DecimalFormat df = new DecimalFormat("#,###.##");

        System.out.println("===== 출판물 통계 분석 =====");
        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> avg = calculateAveragePriceByType(publications);
        for (Map.Entry<String, Double> e : avg.entrySet())
            System.out.println("   - " + e.getKey() + ": " + df.format(e.getValue()) + "원");

        System.out.println("\n2. 출판물 유형 분포:");
        Map<String, Double> dist = calculatePublicationDistribution(publications);
        for (Map.Entry<String, Double> e : dist.entrySet())
            System.out.printf("   - %s: %.2f%%\n", e.getKey(), e.getValue());

        System.out.printf("\n3. 2007년에 출판된 출판물 비율: %.2f%%\n",
                calculatePublicationRatioByYear(publications, "2007"));
    }
}