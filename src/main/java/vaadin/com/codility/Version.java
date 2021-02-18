package vaadin.com.codility;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Version implements Comparable<Version> {

    static final String errorVersionMustNotBeNull = "'version' must not be null!";
    static final String errorOtherMustNotBeNull = "'other' must not be null!";
    static final String errorVersionMustMatchPattern = "'version' must match: 'major.minor.patch(-SNAPSHOT)'!";

    private final String version;

    public Version(String version) {
        if (Objects.isNull(version)) {
            throw new IllegalArgumentException(errorVersionMustNotBeNull);
        }

        if (!version.matches("\\d+(\\.\\d+){0,2}(-SNAPSHOT)?")) {
            throw new IllegalArgumentException(errorVersionMustMatchPattern);
        }
        this.version = version;
    }

    boolean isSnapshot() {
        return version.contains("-SNAPSHOT");
    }

    @Override
    public int compareTo(Version o) {
        if (Objects.isNull(o)) {
            throw new IllegalArgumentException(errorOtherMustNotBeNull);
        }
        String[] v1 = this.version.replace("-SNAPSHOT", "").split("\\.");
        String[] v2 = o.version.split("\\.");

        if (this.isSnapshot() && !o.isSnapshot()) {
            return -1;
        }
        if (!this.isSnapshot() && o.isSnapshot()) {
            return 1;
        }

        for (int i = 0; i < Math.min(v1.length, v2.length); i++) {
            if (Integer.parseInt(v1[i]) > Integer.parseInt(v2[i])) {
                return 1;
            } else if (Integer.parseInt(v1[i]) < Integer.parseInt(v2[i])) {
                return -1;
            }
        }

        if (v1.length > v2.length) {
            return 1;
        } else if (v1.length < v2.length) {
            return -1;
        }
        return 0;
    }

    public static void main(String[] args) {
        List<StringBuilder> list = new ArrayList<>();


        String s[] = "Aenean volutpat aliquam lectus ac laoreet. Sed nibh purus, eleifend sit amet arcu molestie, molestie maximus enim.".split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (builder.length() + s[i].length() <= 30) {
                builder.append(s[i]);
                builder.append(" ");
            } else {
                //list.add(builder);
                System.out.println(String.format("%-30s ", builder.toString()));
                builder = new StringBuilder(s[i] + " ");
            }
        }
        System.out.println(builder.toString());
    }
}
