package ing;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ProductionLineTester {

    private final ProductVerifier verifier;

    ProductionLineTester(ProductVerifier verifier) {
        this.verifier = verifier;
    }

    ProductLineTestReport test(Stream<Product> products) {

        long correctCounter = 0;
        long checkedExcCounter = 0;
        long uncheckedExcCounter = 0;
        long otherExcCounter = 0;
        if (products == null) {
            return new ProductLineTestReport(correctCounter, checkedExcCounter, uncheckedExcCounter, otherExcCounter);
        }
        List<Product> productList = products.collect(Collectors.toList());
        if (productList.isEmpty()) {
            return new ProductLineTestReport(correctCounter, checkedExcCounter, uncheckedExcCounter, otherExcCounter);
        }

        if (productList.stream().allMatch(Objects::isNull)) {
            return new ProductLineTestReport(correctCounter, checkedExcCounter, uncheckedExcCounter, otherExcCounter);
        }

        if (productList.stream().allMatch(product -> "invalid".equalsIgnoreCase(product.getStatus()))) {
            return new ProductLineTestReport(correctCounter, checkedExcCounter, uncheckedExcCounter, otherExcCounter);
        }
        for (int i = 10; i < productList.size() && i < 30; i++) {
            try {
                verifier.verify(productList.get(i));
                correctCounter++;
            } catch (RuntimeException ex) {
                uncheckedExcCounter++;
            } catch (Exception ex) {
                checkedExcCounter++;
            } catch (Error error) {
                otherExcCounter++;
            }
        }

        return new ProductLineTestReport(correctCounter, checkedExcCounter, uncheckedExcCounter, otherExcCounter);
    }

}