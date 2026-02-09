package ru.javaops.bootjava.vote;

import ru.javaops.bootjava.MatcherFactory;
import ru.javaops.bootjava.vote.to.VoteResponseTo;

public class VoteTestData {

    public static final MatcherFactory.Matcher<VoteResponseTo> VOTE_MATCHER =
            MatcherFactory.usingEqualsComparator(VoteResponseTo.class);

    public static final int RESTAURANT_1_ID = 1;
    public static final int RESTAURANT_2_ID = 2;
    public static final int NOT_FOUND = 100;
}
