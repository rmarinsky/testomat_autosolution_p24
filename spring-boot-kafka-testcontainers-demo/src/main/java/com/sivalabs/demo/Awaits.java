package com.sivalabs.demo;

import org.awaitility.core.ConditionFactory;

import java.time.Duration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public interface Awaits {

    ConditionFactory await10SecondPollEverySecond = await().pollInterval(Duration.ofSeconds(3)).atMost(10, SECONDS);

}
