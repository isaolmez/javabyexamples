package com.javabyexamples.spring.core.usingbean.cglibproxying;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class ApplicationConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testThatThereIsSinglePostRepository() {
        final PostRepository postRepository = context
          .getBean("postRepository", PostRepository.class);
        final DefaultPostService firstPostService = context
          .getBean("firstPostService", DefaultPostService.class);
        final DefaultPostService secondPostService = context
          .getBean("secondPostService", DefaultPostService.class);

        assertThat(firstPostService.getPostRepository()).isEqualTo(postRepository);
        assertThat(secondPostService.getPostRepository()).isEqualTo(postRepository);
    }

    @Test
    public void testThatThereAreMultipleLogService() {
        final LogService logService = context.getBean("logService", LogService.class);
        final DefaultPostService firstPostService = context
          .getBean("firstPostService", DefaultPostService.class);
        final DefaultPostService secondPostService = context
          .getBean("secondPostService", DefaultPostService.class);

        assertThat(firstPostService.getLogService()).isNotEqualTo(logService);
        assertThat(secondPostService.getLogService()).isNotEqualTo(logService);
        assertThat(firstPostService.getLogService()).isNotEqualTo(secondPostService.getLogService());
    }
}
