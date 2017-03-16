package methods;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class ArrayContainsSubArray extends TypeSafeDiagnosingMatcher<String[]> {
    private final String[] matchers;

    public ArrayContainsSubArray(String[] matchers) {
        this.matchers = matchers;
    }

    @Override
    protected boolean matchesSafely(String[] item, Description mismatchDescription) {
        return Arrays.asList(item).containsAll(Arrays.asList(matchers));
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("luis over ").appendValueList("[", ", ", "]", matchers).appendText(" in any order");
    }

    @Factory
    public static Matcher<String[]> containsSubArray(String[] itemMatchers) {
        return new ArrayContainsSubArray(itemMatchers);
    }

}