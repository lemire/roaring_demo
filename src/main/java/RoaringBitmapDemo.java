import org.roaringbitmap.RoaringBitmap;
import java.util.HashSet;
import java.util.Random;

public class RoaringBitmapDemo {
    public static void main(String[] args) {
        // Initialisation
        int dataSize = 1_000_000;
        Random random = new Random();
        
        // Création des ensembles
        RoaringBitmap bitmap1 = new RoaringBitmap();
        RoaringBitmap bitmap2 = new RoaringBitmap();
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        
        // Remplissage des ensembles avec des données aléatoires
        System.out.println("Remplissage des ensembles...");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < dataSize; i++) {
            int value = random.nextInt(10_000_000);
            bitmap1.add(value);
            set1.add(value);
        }
        for (int i = 0; i < dataSize; i++) {
            int value = random.nextInt(10_000_000);
            bitmap2.add(value);
            set2.add(value);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Temps de remplissage: " + (endTime - startTime) + " ms");
        
        // Taille en mémoire
        System.out.println("\nTaille en mémoire:");
        System.out.println("RoaringBitmap 1: " + bitmap1.getSizeInBytes() + " bytes");
        System.out.println("RoaringBitmap 2: " + bitmap2.getSizeInBytes() + " bytes");
        // Estimation approximative pour HashSet
        System.out.println("HashSet 1 (estimé): " + (set1.size() * 32) + " bytes");
        System.out.println("HashSet 2 (estimé): " + (set2.size() * 32) + " bytes");
        
        // Opération d'union
        System.out.println("\nOpération d'union...");
        startTime = System.currentTimeMillis();
        RoaringBitmap unionBitmap = RoaringBitmap.or(bitmap1, bitmap2);
        endTime = System.currentTimeMillis();
        System.out.println("Temps union RoaringBitmap: " + (endTime - startTime) + " ms");
        
        startTime = System.currentTimeMillis();
        HashSet<Integer> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);
        endTime = System.currentTimeMillis();
        System.out.println("Temps union HashSet: " + (endTime - startTime) + " ms");
        
        // Opération d'intersection
        System.out.println("\nOpération d'intersection...");
        startTime = System.currentTimeMillis();
        RoaringBitmap intersectionBitmap = RoaringBitmap.and(bitmap1, bitmap2);
        endTime = System.currentTimeMillis();
        System.out.println("Temps intersection RoaringBitmap: " + (endTime - startTime) + " ms");
        
        startTime = System.currentTimeMillis();
        HashSet<Integer> intersectionSet = new HashSet<>(set1);
        intersectionSet.retainAll(set2);
        endTime = System.currentTimeMillis();
        System.out.println("Temps intersection HashSet: " + (endTime - startTime) + " ms");
        
        // Vérification d'appartenance
        System.out.println("\nVérification d'appartenance (100000 tests)...");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            bitmap1.contains(random.nextInt(10_000_000));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Temps contains RoaringBitmap: " + (endTime - startTime) + " ms");
        
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            set1.contains(random.nextInt(10_000_000));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Temps contains HashSet: " + (endTime - startTime) + " ms");
        
        // Cardinalité
        System.out.println("\nCardinalité:");
        System.out.println("Bitmap 1: " + bitmap1.getCardinality());
        System.out.println("Bitmap 2: " + bitmap2.getCardinality());
        System.out.println("Union: " + unionBitmap.getCardinality());
        System.out.println("Intersection: " + intersectionBitmap.getCardinality());
    }
}
