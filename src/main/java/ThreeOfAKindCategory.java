import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

public class ThreeOfAKindCategory extends NumberOfKindCategory {

    @Override
    public int getMinNumberOccurrences() {
        return 3;
    }
}
