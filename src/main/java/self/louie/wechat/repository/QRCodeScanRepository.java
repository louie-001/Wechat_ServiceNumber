package self.louie.wechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import self.louie.wechat.beans.QRCodeScan;

/**
 * Created by louie on 2017-11-15.
 */
@Repository
public interface QRCodeScanRepository extends JpaRepository<QRCodeScan,Long>{
}
