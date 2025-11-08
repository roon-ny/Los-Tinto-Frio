import java.util.*;

// === Clase Cultivo ===
class Cultivo {
    String nombre;
    int diasMaduracion; // días promedio desde siembra hasta recolección

    Cultivo(String nombre, int diasMaduracion) {
        this.nombre = nombre;
        this.diasMaduracion = diasMaduracion;
    }

    @Override
    public String toString() {
        return nombre + " (madura en " + diasMaduracion + " días)";
    }
}

// === Clase Parcela ===
class Parcela {
    int id;
    double x, y;   // coordenadas
    double kg;     // estimación de kilos
    Cultivo cultivo;

    Parcela(int id, double x, double y, double kg, Cultivo cultivo) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.kg = kg;
        this.cultivo = cultivo;
    }

    double distanciaA(Recolector r) {
        return Math.sqrt(Math.pow(x - r.x, 2) + Math.pow(y - r.y, 2));
    }

    @Override
    public String toString() {
        return "Parcela " + id + " (" + x + "," + y + ") - " + kg + "kg (" + cultivo.nombre + ")";
    }
}

// === Clase Recolector ===
class Recolector {
    int id;
    double x, y;              // posición actual
    double cargaAsignada = 0; // total de kg asignados
    List<Parcela> asignadas = new ArrayList<>();

    Recolector(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    void asignar(Parcela p) {
        asignadas.add(p);
        cargaAsignada += p.kg;
        // se mueve a la ubicación de la parcela
        x = p.x;
        y = p.y;
    }

    @Override
    public String toString() {
        return "Recolector " + id + " - Total asignado: " + cargaAsignada + "kg";
    }
}

// === Clase Principal ===
public class AgroSmart {

    public static void main(String[] args) {

        // ====== Tipos de cultivos con sus tiempos promedio ======
        Cultivo cafe = new Cultivo("Café", 220);
        Cultivo maiz = new Cultivo("Maíz", 120);
        Cultivo cacao = new Cultivo("Cacao", 160);
        Cultivo platano = new Cultivo("Plátano", 300);

        // ====== Parcelas de ejemplo ======
        List<Parcela> parcelas = Arrays.asList(
            new Parcela(1, 2, 3, 25, cafe),
            new Parcela(2, 5, 1, 15, maiz),
            new Parcela(3, 7, 4, 20, cacao),
            new Parcela(4, 1, 8, 30, platano),
            new Parcela(5, 4, 7, 10, cafe)
        );

        // ====== Recolectores ======
        List<Recolector> recolectores = Arrays.asList(
            new Recolector(1, 0, 0),
            new Recolector(2, 8, 8)
        );

        // ====== Asignación y reporte ======
        asignarParcelas(parcelas, recolectores);
        mostrarReporte(recolectores);
        mostrarMaduracion(parcelas);
    }

    // === Asignación sencilla (balance distancia / carga) ===
    static void asignarParcelas(List<Parcela> parcelas, List<Recolector> recolectores) {
        List<Parcela> pendientes = new ArrayList<>(parcelas);
        pendientes.sort((a, b) -> Double.compare(b.kg, a.kg)); // parcelas más grandes primero

        while (!pendientes.isEmpty()) {
            Parcela p = pendientes.remove(0);

            Recolector mejor = null;
            double mejorScore = Double.MAX_VALUE;

            for (Recolector r : recolectores) {
                double dist = p.distanciaA(r);
                double score = dist + (r.cargaAsignada * 0.05);
                if (score < mejorScore) {
                    mejorScore = score;
                    mejor = r;
                }
            }

            if (mejor != null) mejor.asignar(p);
        }
    }

    // === Reporte general ===
    static void mostrarReporte(List<Recolector> recolectores) {
        System.out.println("\n📊 RESULTADO DE ASIGNACIÓN DE RECOLECTORES\n");

        double totalKg = 0;
        double velocidadRecoleccion = 10; // kg/hora
        double tiempoTotal = 0;

        for (Recolector r : recolectores) {
            System.out.println(r);
            double tiempo = r.cargaAsignada / velocidadRecoleccion;
            tiempoTotal += tiempo;

            for (Parcela p : r.asignadas) {
                System.out.println("   ➜ " + p);
            }
            System.out.println("   ⏱️ Tiempo estimado de recolección: " + String.format("%.2f", tiempo) + " horas\n");
            totalKg += r.cargaAsignada;
        }

        double promedio = totalKg / recolectores.size();

        System.out.println("📦 Resumen general:");
        System.out.println(" - Total de recolectores: " + recolectores.size());
        System.out.println(" - Carga total: " + totalKg + "kg");
        System.out.println(" - Promedio por recolector: " + String.format("%.1f", promedio) + "kg");
        System.out.println(" - Tiempo total estimado: " + String.format("%.1f", tiempoTotal) + " horas");
        System.out.println("\n✅ Asignación completada con éxito.\n");
    }

    // === Mostrar maduración de los cultivos ===
    static void mostrarMaduracion(List<Parcela> parcelas) {
        System.out.println("🌾 TIEMPOS DE MADURACIÓN DE LOS CULTIVOS:\n");

        Set<String> tipos = new HashSet<>();
        for (Parcela p : parcelas) {
            if (!tipos.contains(p.cultivo.nombre)) {
                System.out.println(" - " + p.cultivo);
                tipos.add(p.cultivo.nombre);
            }
        }

        System.out.println("\n📅 Esto ayuda a planificar cuándo cada cultivo estará listo para cosechar.\n");
    }
}
