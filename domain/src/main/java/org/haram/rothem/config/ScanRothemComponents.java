package org.haram.rothem.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.space.domain.rothem.entity")
@EnableJpaRepositories("com.space.domain.rothem.repository")
@Configuration
public class ScanRothemComponents {}
