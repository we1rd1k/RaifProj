import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class RaifPage {

    public Map<String, Integer> listFormat(List<String> list) throws Exception {
        if (list.stream().anyMatch(Objects::isNull)) {
            throw new Exception("В списке не должно быть null");
        } else if (!list.stream().allMatch(s -> s.matches("^[a-zA-Z]+$"))) {
            throw new Exception("В списке должны быть только латинсткие символы");
        }
        List<String> lst = sortList(list);
        String ss = "";
        Map<String, Integer> countMap = new HashMap<>();
        for (String s : lst) {
            if (!ss.equals(s) & StringUtils.isNotBlank(s)) {
                ss = s;
                Integer counter = Collections.frequency(lst, s);
                countMap.put(ss, counter);
                System.out.println(ss + " : " + counter);
            }
        }
        return countMap;
    }

    public List<String> sortList(List<String> list) {
        return list.stream()
                .map(String::toLowerCase)
                .map(StringUtils::capitalize)
                .sorted()
                .collect(Collectors.toList());
    }
}
