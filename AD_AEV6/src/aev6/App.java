package aev6;

import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

public class App {

	static MongoCollection<Document> coleccion;

	public static void mostrarTots() {
		MongoCursor<Document> cursor = coleccion.find().iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			System.out.println("El llibre amb id: " + obj.getString("Id") + " \n Es: " + obj.getString("Titol"));
		}
	}

	public static void verLlibre(String id) {
		Bson query = eq("Id", id);
		MongoCursor<Document> cursor = coleccion.find(query).iterator();
		while (cursor.hasNext()) {
			JSONObject obj = new JSONObject(cursor.next().toJson());
			System.out.println("ID: " + obj.getString("Id"));
			System.out.println("TITOL: " + obj.getString("Titol"));
			System.out.println("AUTOR: " + obj.getString("Autor"));
			System.out.println("ANY NAIXEMENT: " + obj.getString("Any_naixement"));
			System.out.println("ANY PUBLICACIÓ: " + obj.getString("Any_publicacio"));
			System.out.println("EDITORIAL: " + obj.getString("Editorial"));
			System.out.println("No PÀGINES: " + obj.getString("Nombre_pagines"));
		}

	}

	public static void actualitzarLlibre(String id) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Indica els nous valors per al llibre");
		Bson query = eq("Id", id);
		System.out.print("titol  ");
		String dato = sc.nextLine();
		coleccion.updateOne(query, new Document("$set", new Document("Titol", dato)));
		System.out.print("Autor  ");
		dato = sc.nextLine();
		coleccion.updateOne(query, new Document("$set", new Document("Autor", dato)));
		System.out.print("Any Publicacio ");
		dato = sc.nextLine();
		coleccion.updateOne(query, new Document("$set", new Document("Any_publicacio", dato)));
		System.out.print("Any naixement ");
		dato = sc.nextLine();
		coleccion.updateOne(query, new Document("$set", new Document("Any_naixement", dato)));
		System.out.print("editorial ");
		dato = sc.nextLine();
		coleccion.updateOne(query, new Document("$set", new Document("Editorial", dato)));
		System.out.print("numero de pagines ");
		dato = sc.nextLine();
		coleccion.updateOne(query, new Document("$set", new Document("Nombre_pagines", dato)));
		sc.close();
	}
	public static void nouLlibre() {
		Document document = new Document();
		Scanner sc = new Scanner(System.in);
		System.out.print("introdueix Id: ");
		String id = sc.nextLine();
		document.append("Id", id);
		System.out.print("Introdueix Titol: ");
		String titol = sc.nextLine();
		document.append("Titol", titol);
		System.out.print("Introdueix Autor: ");
		String autor = sc.nextLine();
		document.append("Autor", autor);
		System.out.print("Introdueix Any de Naixement: ");
		String any_naixement = sc.nextLine();
		document.append("Any_naixement", any_naixement);
		System.out.print("Introdueix any de naixement: ");
		String any_publicacio = sc.nextLine();
		document.append("Any_publicacio", any_publicacio);
		System.out.print("Introdueix Editorial: ");
		String editorial = sc.nextLine();
		document.append("Editorial", editorial);
		System.out.print("Introdueix Numero de pagines: ");
		String num_pagines = sc.nextLine();
		document.append("Nombre_pagines", num_pagines);
		coleccion.insertOne(document);
		sc.close();
	}
	public static void borrarLibro(String id) {
		coleccion.deleteOne(eq("Id", id));
		
	}

	public static void main(String[] args) throws InterruptedException {

		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("Biblioteca");
		coleccion = database.getCollection("Llibre");

		Scanner sc = new Scanner(System.in);
		int numero = 0;
		String id;

		while (numero >= 1 && numero <= 5) {
			System.out.println("1--> Ver tots els llibres");
			System.out.println("2--> Informacio completa llibre");
			System.out.println("3--> Afegir un nou llibre");
			System.out.println("4--> Actualitzar un llibre");
			System.out.println("5--> Eliminar un llibre");
			System.out.println("6--> Apagar");
			System.out.print(" Posa el numero de la teua consulta ");
			numero = Integer.parseInt(sc.next());
			switch (numero) {
			case 1:
				mostrarTots();
				break;
			case 2:
				System.out.print("Indica El id del llibre a mostrar ");
				id = sc.next();
				verLlibre(id);
				break;
			case 3:
				nouLlibre();
				break;
			case 4:
				System.out.print("Indica el id del llibre a actualitzar: ");
				id = sc.next();
				actualitzarLlibre(id);
				break;
			case 5:
				System.out.print("Indica el id del llibre a borrar");
				id = sc.next();
				borrarLibro(id);
				break;

			default:
				sc.close();
				mongoClient.close();
				break;
			}
		}

	}

}
