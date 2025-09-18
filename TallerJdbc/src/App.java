import taller.Conexiondb;
import taller.Estudiante;
import taller.EstudianteDAO;
import java.util.Scanner;
import java.util.List;

public class App {

    public static void main(String[] args){
        EstudianteDAO dao = new EstudianteDAO();
        Scanner sc = new Scanner(System.in);
        int option;
        char continua;

        do{
            System.out.println("---------- menu -------------");
            System.out.println("1. Insertar estudiante");
            System.out.println("2. Actualizar estudiante");
            System.out.println("3. Eliminar estudiante");
            System.out.println("4. Consultar todos los estudiantes");
            System.out.println("5. Consultar todos por correo");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1: 
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Apellido: ");
                String apellido = sc.nextLine();
                System.out.print("Correo: ");
                String correo = sc.nextLine();
                System.out.print("Edad: ");
                int edad = sc.nextInt();
                sc.nextLine();
                System.out.print("estado civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO)");
                String estadocivil = sc.nextLine();

                Estudiante nuevo = new Estudiante(nombre, apellido, correo, edad, estadocivil);
                dao.insertar(nuevo);
                break;

                case 2:
                System.out.print("Correo del estudiante a actualizar: ");
                String correoUpdate = sc.nextLine();
                Estudiante est = dao.consultarPorCorreo(correoUpdate);
                if (est != null) {
                    System.out.print("nuevo nombre: ");
                    est.setNombre(sc.nextLine());
                    System.out.print("nuevo apellido: ");
                    est.setApellido(sc.nextLine());
                    System.out.print("nueva edad: ");
                    est.setEdad(sc.nextInt());
                    sc.nextLine();
                    System.out.print("nuevo estado civil: ");
                    est.setEstadoCivil(sc.nextLine());
                    dao.actualizar(est);
                } else {
                    System.out.println("estudiante no encontrado.");
                }
                break;

                case 3:
                System.out.print("correo del estudiante a eliminar: ");
                String correoEliminar = sc.nextLine();
                dao.eliminar(correoEliminar);
                break;

                case 4:
                List<Estudiante> lista = dao.consultarTodos();
                if(lista.isEmpty()){
                    System.out.println("no hay estudiantes registrados");
                }else{
                    lista.forEach(System.out::println);
                }
                break;

                case 5:
                System.out.print("correo del estudiante a consultar:");
                String correoCon = sc.nextLine();
                Estudiante encontrado = dao.consultarPorCorreo(correoCon);
                if(encontrado != null)
                    System.out.println(encontrado);
                else 
                    System.out.println("estudiante no encontrado");
                break;

                case 6:
                System.out.println("programa finalizado");
                break;

                default:
                  System.out.println("opción inválida");
            }
            System.out.print("¿Desea continuar en el menú? (s/n): ");
            continua = sc.next().charAt(0);

        } while (continua == 's' || continua == 'S');

        System.out.println("Programa finalizado.");
        sc.close();
    }
    
    // public static void main(String[] args) throws Exception {
    //     // try {
    //     //     java.sql.Connection conn = Conexiondb.getConnection();
    //     //     System.out.println("Conexión exitosa a la base de datos.");
    //     //     conn.close();
    //     // } catch(Exception e){
    //     //     System.out.println("error de conexión: "+ e.getMessage());
    //     // }
    //     // System.out.println("Hello, World!");
    // }
}
