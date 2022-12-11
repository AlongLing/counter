package thirdpart.uuid;

/**
 * @author lqs
 * @version 1.0.0
 * @date 2022年12月11日 13:07:41
 * @packageName thirdpart.uuid
 * @className GudyUuid
 * @describe TODO
 */
public class GudyUuid {

    private static GudyUuid ourInstance = new GudyUuid();

    public static GudyUuid getInstance() {
        return ourInstance;
    }

    private GudyUuid() {
    }

    private SnowflakeIdWorker idWorker;

    public void init(long centerId, long workerId) {
        idWorker = new SnowflakeIdWorker(workerId, centerId);
    }

    public long getUUID() {
        return idWorker.nextId();
    }

}
