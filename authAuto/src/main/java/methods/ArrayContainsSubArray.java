package methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ArrayContainsSubArray extends TypeSafeMatcher<String[]> {

    private final String[] matchers;

    public ArrayContainsSubArray(String[] matchers) {
        this.matchers = matchers;
    }

    @Override
    protected boolean matchesSafely(String[] item) {
        return Arrays.asList(item).containsAll(Arrays.asList(matchers));
    }

    @Override
    protected void describeMismatchSafely(String[] item, Description mismatchDescription) {
        List<String> missingElements = new ArrayList<>();
        for (String m : matchers) {
            if (!Arrays.asList(item).contains(m)) {
                missingElements.add(m);
            }
        }
        mismatchDescription.appendText("the following elements are missing ").appendValueList("[", ", ", "]", missingElements);
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