package org.example.sem8_dz;

import org.example.sem8_dz.aspect.RecoverException;
import org.example.sem8_dz.aspect.Timer;
import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import org.example.sem8_dz.aspect.Loggable;

@Timer
//@Loggable(level = Level.INFO)
@Component
public class Mario implements Brother {

    public void method1(String arg1, int arg2) {

    }

    public String method2() {
        return "value";
    }

    @RecoverException(noRecoverFor = {RuntimeException.class})
    public String method3() {
        throw new RuntimeException("runtimeexceptionmsg");
    }

    public void method4() throws InterruptedException {
        Thread.sleep(1020);
    }

}
