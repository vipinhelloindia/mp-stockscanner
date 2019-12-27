package com.mp.stockscanner.base;


import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.mp.stockscanner.di.component.TestComponent;

import org.junit.After;
import org.junit.Before;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


public class BaseTest<T> {
    private MockWebServer mockServer;
    private TestComponent testComponent;
    Observer<T> observer = null;

    @Before
    public void setUp() throws Exception {
        configureDi();
        MockitoAnnotations.initMocks(this);
        /*configureMockServer for different status code handling*/
//        configureMockServer();
    }

    private void configureMockServer() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
    }

    @After
    public void tearDown() throws Exception {
//        stopMockServer();
    }

    private void stopMockServer() throws IOException {
        mockServer.shutdown();
    }

    // CONFIGURATION
    public void configureDi() {
//        testComponent = DaggerTestComponent.builder().build();
//        testComponent.inject(this);
    }

    public void mockHttpResponse(String fileName, int responseCode) {
        mockServer.enqueue(new MockResponse()
                .setResponseCode(responseCode)
                .setBody(getJson(fileName)));
    }

    private String getJson(String path) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            Path currentRelativePath = Paths.get("");
            is = this.getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            String line = buf.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

    protected void getValue(final LiveData liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                t.hashCode();
            }
        };
        liveData.observeForever(observer);
        latch.await(2, java.util.concurrent.TimeUnit.SECONDS);
    }
}