package curs14;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoExample {


	//Conect to MongoDb
	public static MongoClient connectToMongo() {
		try {
		ConnectionString connectionString = new ConnectionString("mongodb+srv://testuser:testuser@cluster0.bkai1.mongodb.net/sample_analytics?retryWrites=true&w=majority");
		MongoClientSettings settings =  MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build())
				.build();				
		MongoClient mongoClient =  MongoClients.create(settings);

		return mongoClient;
		
		}catch(MongoException e) {
			System.out.println("Could not establish a connection!");
			e.printStackTrace();
		}
		return null;
	}
	
	//close mongo connection
	public static void closeMongoConnection(MongoClient client) {
		
		try {
			System.out.println("Trying to close the connection");
			client.close();
			System.out.println("Connection closed sucessfully!");
	
		}catch(MongoException e) {
			
			System.out.println("Could not close the connection");
			e.printStackTrace();
		}
		
		
	}
	
	//create mongo collection
	public static void createMongoCollection(MongoDatabase db, String collectionName) {
		
		try {
			System.out.println("Trying to create collection :" + collectionName);
			db.createCollection(collectionName);
			System.out.println(collectionName + " created sucessfully!");

			
		}catch(MongoException e) {
			System.out.println("Could not create collection");
			e.printStackTrace();
		}
	}
	
	//delete mongo collection
	public static void deleteMongoCollection(MongoCollection<Document> collection) {
		
		try {
			System.out.println("Trying to delete collection");
			collection.drop();
			System.out.println("Collection deleted sucessfully!");

			
		}catch(MongoException e) {
			System.out.println("Could not create collection");
			e.printStackTrace();
		}
	}
	
	
	
	//get mongo collection
	public static MongoCollection<Document> getMongoCollection(MongoDatabase db, String collectionName){
		
		MongoCollection<Document> collection;
		
		try {
			System.out.println("trying to read colection :" + collectionName);
			collection = db.getCollection(collectionName);
			System.out.println(collectionName + " successfully get");
			return collection;

		}catch(MongoException e) {
			System.out.println("Could not get the collection");
			e.printStackTrace();
		}
		return null;	
	}
	
	//insert into collection
	public static void insertOneDocumentIntoColelction(MongoCollection<Document> collection, Document doc) {
		
		try {
			System.out.println("Trying to insert document");
			collection.insertOne(doc);
			System.out.println("Document inserted sucessfully");

		}catch(MongoException e) {
			System.out.println("Could not insert into collection ");
			e.printStackTrace();
		}
		
	}
	
	//read nr of records from a collection
	public static long readNrOfDocumentsInsideCollection(MongoCollection<Document> collection) {
		
		try {
			return collection.countDocuments();
			
		}catch(MongoException e) {
			System.out.println("Could not read number of documents");
			throw new MongoException(e.getMessage());
		}
	}
	
	//insert many documents
	public static void insertManyDocumentsInCollection(MongoCollection<Document> collection,Document...documents) {
		
		List<Document> docs = new ArrayList<Document>();

		for(Document doc : documents) {
			docs.add(doc);
			}
		
		try {			
			System.out.println("Trying to insert document");
			collection.insertMany(docs);
			System.out.println("Document inserted sucessfully");

		}catch(MongoException e) {
			System.out.println("Could not insert into collection ");
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		
		//create the mongo client and coonect to server
		MongoClient mongoClient = connectToMongo();
		//create MongoDatabase obj and connect to BD
		MongoDatabase databaseSampleAnalytics = mongoClient.getDatabase("sample_analytics");
		//create new collection inside the above database		
		createMongoCollection(databaseSampleAnalytics, "new customers");

		//list all collections from the database
		for(String name : databaseSampleAnalytics.listCollectionNames()) {
			System.out.println("Collection name :" + name);		
		}
		//create a mongo coollection obj which will represent a collection to work with
		MongoCollection<Document> newCustomers = getMongoCollection(databaseSampleAnalytics, "new customers");
		
		//reads the nr documnets from a collection
		System.out.println("Count documents in newCustomer :" +readNrOfDocumentsInsideCollection(newCustomers));
		
		//create a document to store into collection
		Document customer =  new Document("_id", new ObjectId())
				.append("name","Customer")
				.append("credit", 100)
				.append("address", "str. sperantei parter")
				.append("email", "customer@mongotest.mongo");
		
		//insert the above document into a collection
		insertOneDocumentIntoColelction(newCustomers, customer);
		
		//reads the nr documnets from a collection
		System.out.println("Count documents in newCustomer :" +readNrOfDocumentsInsideCollection(newCustomers));
		
		//create multiple documents to store into collection
		Document customer2 =  new Document("_id", new ObjectId())
				.append("name","Customer2")
				.append("credit", 100)
				.append("address", "str. sperantei parter")
				.append("email", "customer@mongotest.mongo");
		Document customer3 =  new Document("_id", new ObjectId())
				.append("name","Customer4")
				.append("credit", 100)
				.append("address", "str. sperantei parter")
				.append("email", "customer@mongotest.mongo");
		Document customer4 =  new Document("_id", new ObjectId())
				.append("name","Customer5")
				.append("credit", 100)
				.append("address", "str. sperantei parter")
				.append("email", "customer@mongotest.mongo");
		
		//we use varargs for multiple documents
		insertManyDocumentsInCollection(newCustomers, customer2, customer3, customer4);
		
		System.out.println("Count documents in newCustomer :" +readNrOfDocumentsInsideCollection(newCustomers));
		
		
	//	Document obj = newCustomers.find(new Document("credit", 100)).first();
	//	System.out.println(obj.toJson());
		
		//update field into document
		newCustomers.updateOne(
				Filters.eq("name", "Customer4"),
				Updates.set("credit", 250));
		
		Document obj2 = newCustomers.find(new Document("name", "Customer4")).first();
		System.out.println(obj2.toJson());
		
		Bson filter = Filters.eq("name", "Customer4");
		Bson update = Updates.set("address", "no adresss");
		Bson update2 = Updates.set("email", "no email");
		Bson updates = Updates.combine(update, update2);
		
		newCustomers.findOneAndUpdate(filter, updates);

		Document obj3 = newCustomers.find(new Document("name", "Customer4")).first();
		System.out.println(obj3.toJson());
		
		//delete one document
		Bson filter1 = Filters.eq("name", "Customer5");
		newCustomers.findOneAndDelete(filter1);
		
		//delete many documents
		Bson filter2 =  Filters.eq("credit", 100);
		newCustomers.deleteMany(filter2);
		
		//delete collection
		deleteMongoCollection(newCustomers);
		
		for(String name : databaseSampleAnalytics.listCollectionNames()) {
			System.out.println("Collection name :" + name);		
		}
		
		
		//closes mongo connection
		closeMongoConnection(mongoClient);
		
		
	}
	
}
