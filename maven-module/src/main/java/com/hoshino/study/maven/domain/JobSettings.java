package com.hoshino.study.maven.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author huangyuehao
 * @date 2022-10-21
 */
@Data
public final class JobSettings implements Serializable {

    private static final long serialVersionUID = -6532210090618686688L;

    private String jobName;

    private String jobType;

    private String jobClass;

    private String cron;

    private int shardingTotalCount;

    private String shardingItemParameters;

    private String jobParameter;

    private boolean monitorExecution;

    private boolean streamingProcess;

    private int maxTimeDiffSeconds;

    private int monitorPort = -1;

    private boolean failover;

    private boolean misfire;

    private String jobShardingStrategyClass;

    private String description;

    private Map<String, String> jobProperties = new LinkedHashMap<>();

    private String scriptCommandLine;

    private int reconcileIntervalMinutes;
}
