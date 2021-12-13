package aev5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {

	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		// Obri una nova sessió de la session factory
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.getTransaction().commit();

		do {
			System.out.println("Opcions: " + "\n1. Mostrar els titols dels llibres"
					+ "\n2. Mostrar informacio d'un llibre" + "\n3. Crear un nou llibre" + "\n4. Actualitzar un  llibre"
					+ "\n5. Borrar un llibre" + "\n6. Tancar la biblioteca");

			Scanner sc = new Scanner(System.in);
			String resposta = sc.nextLine();
			switch (resposta) {
			case "1": {
				List<Llibre> llibre = llegirLlibres(session);
				for (int i = 0; i < llibre.size(); i++) {
					System.out.println(llibre.get(i).getId() + " " + llibre.get(i).getTitol());
				}
				break;
			}
			case "2": {
				System.out.println("Introdueix la id del llibre: ");
				mostrarLlibre(recuperarLlibre(session),session);

				break;
			}
			case "3": {

				break;
			}
			case "4": {
				System.out.println("Introdueix l'identificador del llibre a actualitzar");
				actualitzarLlibre(sc.nextInt(),session);

				break;
			}
			case "5": {
				System.out.println("Introdueix l'identificador del llibre per eliminar-ho: ");
				borrarLlibre(session,recuperarLlibre(session));

				break;
			}
			case "6": {
				session.close();
				System.exit(0);

			}
			default: {
			}
				System.out.println("Has de triar una opcio de 1 a 6");
				break;
			}
			System.out.println("<Pressiona ENTER per a continuar>");
			sc.nextLine();

		} while (true);

	}

	private static List<Llibre> llegirLlibres(Session session) {
		// TODO Auto-generated method stub
		List <Llibre> listaLlibres= new ArrayList <Llibre>();
		listaLlibres= session.createQuery("FROM Llibre").list() ;
		

		return listaLlibres;
	}

	public static void actualitzarLlibre(int id, Session session) {
		Llibre lli= (Llibre) session.load(Llibre.class, id);
		lli.setAny_Publicacio("1954");
		lli.setEditorial("Saturno");
		session.update(lli);
	}



	public static void crearLlibre(Session session) {
		Scanner sc = new Scanner(System.in);
		String novaDada = sc.nextLine();
		System.out.println("Introdueix el titol del nou llibre: ");
		String nouTitol = sc.nextLine();
		System.out.println("Introdueix l'autor del nou llibre: ");
		String nouAutor = sc.nextLine();
		System.out.println("Introdueix l'any de naixement de l'autor: ");
		String nouAnyNaixement = sc.nextLine();
		System.out.println("Introdueix l'any de publicacio del nou llibre: ");
		String nouAnyPublicacio = sc.nextLine();
		System.out.println("Introdueix l'editorial del nou llibre: ");
		String novaEditorial = sc.nextLine();
		System.out.println("Introdueix el nombre de pagines del nou llibre: ");
		String nouNombrePagines = sc.nextLine();

		Llibre noullibre = new Llibre(nouTitol, nouAutor, nouAnyNaixement, nouAnyPublicacio, novaEditorial,
				nouNombrePagines);
		Serializable id = session.save(noullibre);
		session.getTransaction().commit();
	}

	public static void mostrarLlibre(Llibre llibre, Session session) {
		System.out.println(llibre.id);
		System.out.println(llibre.titol);
		System.out.println(llibre.autor);
		System.out.println(llibre.any_Naixement);
		System.out.println(llibre.any_Publicacio);
		System.out.println(llibre.editorial);
		System.out.println(llibre.nombrePagines);
	}

	public static Llibre recuperarLlibre(Session session) {
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		Llibre llibre = (Llibre) session.get(Llibre.class, id);
		return llibre;
	}

	public static void borrarLlibre(Session session, Llibre llibre) {
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		llibre.setId(id);
		session.delete(llibre);
		session.getTransaction().commit();

	}
}
