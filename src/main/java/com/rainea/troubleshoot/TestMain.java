package com.rainea.troubleshoot;

import com.rainea.troubleshoot.model.Product;
import com.rainea.troubleshoot.model.ProductF1;
import com.rainea.troubleshoot.model.ProductF2;
import sun.misc.VM;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author liulang
 * @date 2021-08-25
 **/
public class TestMain {
    public static void main(String[] args) throws InterruptedException {
//        Product product = new Product();
//        product.setId("pppppppppppppppppppppppppppppppppppppppppppppppppppppp");
//        product.setPrice(11234.324);
//
//        ProductF1 productF1 = new ProductF1();
//        productF1.setId("111");
//        productF1.setProduct(product);
//        productF1.setAge(234);
//
//        ProductF2 productF2 = new ProductF2();
//        productF2.setId("111");
//        productF2.setProduct(product);
//        productF2.setAge(555);
//
//        Thread.sleep(5000000);
        long l = VM.maxDirectMemory();
        System.out.println(l);
        System.out.println(Runtime.getRuntime().maxMemory());
    }
}
