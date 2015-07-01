
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrea
 */
class StringSetGenerator {
     private int size1;
    private int size2;
    private int size0;

    private int sizeBlockUnique;
    private int sizeBlock1;
    private int sizeBlock2;

    private HashSet<String> block1, block2, blockUnique, list1, list2;

    private StringHelper helper = new StringHelper();

    /**
     * Initializes sizes of the first and the second lists and the number of
     * elements that they have in common.
     *
     * @param m1 the first list size
     * @param m2 the second list size
     * @param m0 the number of common elements.
     * @throws Exception if the number of common elements exceeds size of at
     * least one list
     */
    public StringSetGenerator(int m1, int m2, int m0) throws Exception {
        if (m0 > m1 || m0 > m2 || m0 < 0) {
            throw new Exception("The number of common elements must be in range [0, min(m1, m2)].");
        }

        this.size1 = m1;
        this.size2 = m2;
        this.size0 = m0;

        this.sizeBlockUnique = m0;
        this.sizeBlock1 = m1 - this.sizeBlockUnique;
        this.sizeBlock2 = m2 - this.sizeBlockUnique;
    }

    public String info() {
        return "Lists with " + size1 + " and " + size2 + " elements and  "
                + size0 + " eommon elements correspond to the following splitting:\n"
                + "list1 of size " + size1 + " -> " + sizeBlock1 + " + " + sizeBlockUnique
                + "\nlist2 of size " + size2 + " -> " + sizeBlock2 + " + " + sizeBlockUnique;
    }

    void generate(int minSize, int maxSize) throws Exception {
        HashSet<String> bigList = helper.generateRandomStrings(sizeBlock1 + sizeBlock2 + sizeBlockUnique, minSize, maxSize);
        ArrayList<String> list = new ArrayList<String>(bigList);
        this.block1 = new HashSet<String>(list.subList(0, sizeBlock1));
        this.block2 = new HashSet<String>(list.subList(sizeBlock1, sizeBlock1 + sizeBlock2));
        this.blockUnique = new HashSet<String>(list.subList(sizeBlock1 + sizeBlock2, sizeBlock1 + sizeBlock2 + sizeBlockUnique));

        this.list1 = intertwine(this.block1, this.blockUnique);
        this.list2 = intertwine(this.block2, this.blockUnique);
    }

    public HashSet<String> intertwine(HashSet<String> l1, HashSet<String> l2) {
        ArrayList<String> list = new ArrayList<String>(l1);
        list.addAll(l2);
        Collections.shuffle(list, new Random(System.nanoTime()));
        return new HashSet<String>(list);
    }

    public HashSet<String> getFirst() {
        return this.list1;
    }

    public HashSet<String> getSecond() {
        return this.list2;
    }

}
