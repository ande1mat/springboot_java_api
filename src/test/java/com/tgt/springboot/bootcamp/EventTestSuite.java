package com.tgt.springboot.bootcamp;

import com.tgt.springboot.bootcamp.controller.EventControllerTest;
import com.tgt.springboot.bootcamp.repository.EventRepositoryTest;
import com.tgt.springboot.bootcamp.service.EventServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 4/4/18.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({

                            /****** CONTROLLER TESTS **********/
                            EventControllerTest.class,

                            /****** SERVICE TESTS **********/
                            EventServiceTest.class,

                            /******Repository TESTS**********/
                            EventRepositoryTest.class,
                    })
public class EventTestSuite {

}
