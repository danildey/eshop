package com.epam.preprod.eshop.wrappers;

import com.epam.preprod.eshop.entity.Product;
import com.epam.preprod.eshop.entity.TextBook;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StringHCWrappersTest {

    Map<StringSumHashWrapper, Product> SumHM;
    Map<StringLengthHashWrapper, Product> LengthHM;

    Map<StringSumHashWrapper, Product> SumLHM;
    Map<StringLengthHashWrapper, Product> LengthLHM;

    StringSumHashWrapper strSum1;
    StringSumHashWrapper strSum2;
    StringSumHashWrapper strSum3;
    StringSumHashWrapper strSum4;

    StringLengthHashWrapper strLength1;
    StringLengthHashWrapper strLength2;
    StringLengthHashWrapper strLength3;
    StringLengthHashWrapper strLength4;

    Product p1;
    Product p2;
    Product p3;
    Product p4;

    @Before
    public void setUp() {
        SumHM = new HashMap<>();
        LengthHM = new HashMap<>();
        SumLHM = new LinkedHashMap<>();
        LengthLHM = new LinkedHashMap<>();

        strSum1 = new StringSumHashWrapper("b_aaa");
        strSum2 = new StringSumHashWrapper("c_aaa");
        strSum3 = new StringSumHashWrapper("d_aaa");
        strSum4 = new StringSumHashWrapper("f_aaa");

        strLength1 = new StringLengthHashWrapper("1_");
        strLength2 = new StringLengthHashWrapper("2___");
        strLength3 = new StringLengthHashWrapper("3____");
        strLength4 = new StringLengthHashWrapper("4_____");

        p1 = new TextBook("s1", "c1");
        p2 = new TextBook("s2", "c2");
        p3 = new TextBook("s3", "c3");
        p4 = new TextBook("s4", "c4");

    }

    @Test
    public void orderShouldDependsOnSumOfFirstFourChars() throws Exception {
        SumHM.put(strSum1, p1);
        SumHM.put(strSum2, p2);
        SumHM.put(strSum3, p3);
        SumHM.put(strSum4, p4);
        StringSumHashWrapper[] obj = new StringSumHashWrapper[]{strSum1, strSum2, strSum3, strSum4};
        Iterator sumHMIter = SumHM.keySet().iterator();
        int i = 0;
        while (sumHMIter.hasNext()) {
            assertEquals(obj[i++], sumHMIter.next());
        }

    }

    @Test
    public void orderShouldDependsOnCharacterLength() throws Exception {
        LengthHM.put(strLength1, p1);
        LengthHM.put(strLength2, p2);
        LengthHM.put(strLength3, p3);
        LengthHM.put(strLength4, p4);
        StringLengthHashWrapper[] obj = new StringLengthHashWrapper[]{strLength1, strLength2, strLength3, strLength4};
        Iterator sumHMIter = LengthHM.keySet().iterator();
        int i = 0;
        while (sumHMIter.hasNext()) {
            assertEquals(obj[i++], sumHMIter.next());
        }

    }

    @Test
    public void orderShouldNotDependsOnSumOfFirstFourChars() throws Exception {
        SumLHM.put(strSum2, p1);
        SumLHM.put(strSum3, p2);
        SumLHM.put(strSum1, p3);
        SumLHM.put(strSum4, p4);
        StringSumHashWrapper[] obj = new StringSumHashWrapper[]{strSum2, strSum3, strSum1, strSum4};
        Iterator sumHMIter = SumLHM.keySet().iterator();
        int i = 0;
        while (sumHMIter.hasNext()) {
            assertEquals(obj[i++], sumHMIter.next());
        }

    }

    @Test
    public void orderShouldNotDependsOnCharacterLength() throws Exception {
        LengthLHM.put(strLength2, p1);
        LengthLHM.put(strLength3, p2);
        LengthLHM.put(strLength1, p3);
        LengthLHM.put(strLength4, p4);
        StringLengthHashWrapper[] obj = new StringLengthHashWrapper[]{strLength2, strLength3, strLength1, strLength4};
        Iterator sumHMIter = LengthLHM.keySet().iterator();
        int i = 0;
        while (sumHMIter.hasNext()) {
            assertEquals(obj[i++], sumHMIter.next());
        }

    }
}