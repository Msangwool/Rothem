package org.haram.rothem.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("org.haram.rothem.data.entity")
@EnableJpaRepositories("org.haram.rothem.repository")
@Configuration
public class ScanRothemComponents {}
