package dev.jongking.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Component
// Filter 이름 :Logging // GateWayFilterFactory가 지워짐
public class LoggingGatewayFilterFactory
    extends AbstractGatewayFilterFactory<LoggingGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(LoggingGatewayFilterFactory.class);

    // Global Filter가 아니라 yml에 설정해줘야함함

    public LoggingGatewayFilterFactory(){
        super(Config.class);
    }

//    @Override
//    public String name() {
//        return "필터 이름 바꿀 수 있음";
//    }

    @Override
    public GatewayFilter apply(LoggingGatewayFilterFactory.Config config) {
        return (((exchange, chain) -> {
            logger.trace(config.toString());
            String uid = config.simpleUid ?
                    UUID.randomUUID().toString().split("-")[0] :
                    UUID.randomUUID().toString();
            final long timeStart = Instant.now().toEpochMilli();

            return chain.filter(exchange)
                    .then(Mono.fromRunnable(() ->{
                        long timeDiff = Instant.now().toEpochMilli() - timeStart;
                        if(config.inSeconds) timeDiff/=1000;
                        logger.info("Execution Time id: {}, timediff({}): {}",
                                uid, config.inSeconds?"sec":"ms",
                                timeDiff);
                    }));
        }));
    }

    public static class Config{
        private boolean simpleUid;
        private boolean inSeconds;

        public Config() {
        }

        public Config(boolean simpleUid, boolean inSeconds) {
            this.simpleUid = simpleUid;
            this.inSeconds = inSeconds;
        }

        public boolean isSimpleUid() {
            return simpleUid;
        }

        public void setSimpleUid(boolean simpleUid) {
            this.simpleUid = simpleUid;
        }

        public boolean isInSeconds() {
            return inSeconds;
        }

        public void setInSeconds(boolean inSeconds) {
            this.inSeconds = inSeconds;
        }

        @Override
        public String toString() {
            return "config{" +
                    "simpleUid=" + simpleUid +
                    ", inSeconds=" + inSeconds +
                    '}';
        }
    }
}
