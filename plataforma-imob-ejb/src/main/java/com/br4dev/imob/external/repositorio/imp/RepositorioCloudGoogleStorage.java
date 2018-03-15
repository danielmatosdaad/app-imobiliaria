package com.br4dev.imob.external.repositorio.imp;
//
//import java.util.Arrays;
//import java.io.*;
//import java.net.URI;
//import java.net.URL;
//import java.nio.ByteBuffer;
//import java.nio.channels.Channels;
//import java.nio.channels.WritableByteChannel;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.oauth2.Oauth2;
//import com.google.api.services.oauth2.Oauth2Scopes;
//import com.google.api.services.oauth2.model.Userinfoplus;
//import com.google.auth.oauth2.ServiceAccountCredentials;
//import com.google.cloud.ReadChannel;
//import com.google.cloud.WriteChannel;
//import com.google.cloud.storage.Blob;
//import com.google.cloud.storage.BlobId;
//import com.google.cloud.storage.BlobInfo;
//import com.google.cloud.storage.Bucket;
//import com.google.cloud.storage.BucketInfo;
//import com.google.cloud.storage.CopyWriter;
//import com.google.cloud.storage.Storage;
//import com.google.cloud.storage.Storage.BlobGetOption;
//import com.google.cloud.storage.Storage.CopyRequest;
//import com.google.cloud.storage.StorageOptions;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.jboss.shrinkwrap.api.asset.UrlAsset;
//
//import static java.nio.charset.StandardCharsets.UTF_8;
//
public class RepositorioCloudGoogleStorage {
//
//	public static final boolean SERVE_USING_BLOBSTORE_API = false;
//	private static final String PROJECT_ID = "storage-imagem-imob";
//	private static final String BUCKET_NAME_DEFAULT = "storage-imagem-imob.appspot.com";
//	public static final String GCS_HOST = "https://storage.googleapis.com/";
//
//	private Storage storage_service;
//
//	public void autenticar() throws IOException {
//
//		HttpTransport httpTransport = new NetHttpTransport();
//		JacksonFactory jsonFactory = new JacksonFactory();
//
//		// GOOGLE_APPLICATION_CREDENTIALS
//		String SERVICE_ACCOUNT_JSON_FILE = "SERVICE_ACCOUNT_JSON_FILE.json";
//
//		URL urlKey = RepositorioCloudGoogleStorage.class.getResource(SERVICE_ACCOUNT_JSON_FILE);
//
//		try (FileInputStream inputStream = new FileInputStream(new File(urlKey.getPath()))) {
//
//			GoogleCredential credential = GoogleCredential.fromStream(inputStream, httpTransport, jsonFactory);
//			if (credential.createScopedRequired())
//				credential = credential.createScoped(Arrays.asList(Oauth2Scopes.USERINFO_EMAIL));
//
//			Oauth2 service = new Oauth2.Builder(httpTransport, jsonFactory, credential)
//					.setApplicationName("oauth client").build();
//			Userinfoplus ui = service.userinfo().get().execute();
//			System.out.println(ui.getEmail());
//
//		}
//
//		// Using Google Cloud APIs
//		try (FileInputStream inputStreamSac = new FileInputStream(new File(urlKey.getPath()))) {
//			ServiceAccountCredentials sac = ServiceAccountCredentials.fromStream(inputStreamSac);
//			this.storage_service = StorageOptions.newBuilder().setProjectId(PROJECT_ID).setCredentials(sac).build()
//					.getService();
//
//		}
//
//	}
//
//	public void listarTodos() {
//
//		Iterator<Bucket> bucketIterator = storage_service.list().iterateAll();
//		while (bucketIterator.hasNext()) {
//			Bucket bucket = bucketIterator.next();
//			System.out.println(bucket);
//			System.out.println(bucket.getLocation());
//
//			System.out.println("My blobs:");
//
//			// List the blobs in a particular bucket
//			Iterator<Blob> blobIterator = bucket.list().iterateAll();
//			System.out.println("My blobs:");
//			while (blobIterator.hasNext()) {
//				System.out.println(blobIterator.next());
//
//			}
//		}
//
//	}
//
//	public void downloadoAll() {
//
//		Iterator<Bucket> bucketIterator = storage_service.list().iterateAll();
//		while (bucketIterator.hasNext()) {
//			Bucket bucket = bucketIterator.next();
//			System.out.println(bucket);
//			System.out.println(bucket.getLocation());
//
//			System.out.println("My blobs:");
//
//			// List the blobs in a particular bucket
//			Iterator<Blob> blobIterator = bucket.list().iterateAll();
//			System.out.println("My blobs:");
//			while (blobIterator.hasNext()) {
//				System.out.println(blobIterator.next());
//
//				String pathimage = "/Users/daniel-matos/Documents/Projetos/workspace-imob/plataforma-imob/plataforma-imob-ejb/src/main/java/com/br4dev/imob/external/repositorio/imp/"
//						+ blobIterator.next().blobId().getName();
//				File f = new File(pathimage);
//				Path path = Paths.get(pathimage);
//				try {
//					download(storage_service, blobIterator.next().blobId(), path);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
//
//	public void salvar(Long id, String blobName, ByteArrayOutputStream out, String contentType)
//			throws FileNotFoundException, IOException {
//
//		blobName = id.toString() + blobName;
//		System.out.println("blob name " + blobName);
//		Blob blob = storage_service.get(RepositorioCloudGoogleStorage.BUCKET_NAME_DEFAULT, blobName);
//		System.out.println("blob buscado" + blob.toString());
//		BlobId BlobId = com.google.cloud.storage.BlobId.of(RepositorioCloudGoogleStorage.BUCKET_NAME_DEFAULT, blobName);
//		BlobInfo blobInfo = blob.newBuilder(BlobId).build();
//		Bucket bucket = storage_service.get(BUCKET_NAME_DEFAULT);
//		Blob blobCriado = storage_service.create(blobInfo, out.toByteArray());
//		System.out.println("Blob Criado" + blobCriado.toString());
//	}
//
//	public void upload(Storage storage, Path uploadFrom, BlobInfo blobInfo) throws IOException {
//		if (Files.size(uploadFrom) > 1_000_000) {
//			// When content is not available or large (1MB or more) it is
//			// recommended
//			// to write it in chunks via the blob's channel writer.
//			try (WriteChannel writer = storage.writer(blobInfo)) {
//				byte[] buffer = new byte[1024];
//				try (InputStream input = Files.newInputStream(uploadFrom)) {
//					int limit;
//					while ((limit = input.read(buffer)) >= 0) {
//						try {
//							writer.write(ByteBuffer.wrap(buffer, 0, limit));
//						} catch (Exception ex) {
//							ex.printStackTrace();
//						}
//					}
//				}
//			}
//		} else {
//			byte[] bytes = Files.readAllBytes(uploadFrom);
//			storage.create(blobInfo, bytes);
//		}
//		System.out.println("Blob foi criado");
//	}
//
//	public BlobInfo criarBlobInfo(String bucket, String blob, String contentType) throws IOException {
//		BlobInfo blobInfo = BlobInfo.newBuilder(bucket, blob).setContentType(contentType).build();
//		return blobInfo;
//	}
//	
//	public static BlobInfo criarBlobInfo(String bucket, String pasta, String id,String nome,String contentType) throws IOException {
//		if(pasta == null || id==null || nome == null){
//			throw new RuntimeException("Dados obrigatorio invalidos");
//		}
//		
//		String blob = pasta.concat("/").concat(id).concat("/").concat(nome);
//		
//		BlobInfo blobInfo = BlobInfo.newBuilder(bucket, blob).setContentType(contentType).build();
//		return blobInfo;
//	}
//
//	private void download(Storage storage, BlobId blobId, Path downloadTo) throws IOException {
//		Blob blob = storage.get(blobId);
//		if (blob == null) {
//			System.out.println("No such object");
//			return;
//		}
//		PrintStream writeTo = System.out;
//		if (downloadTo != null) {
//			writeTo = new PrintStream(new FileOutputStream(downloadTo.toFile()));
//		}
//		if (blob.getSize() < 1_000_000) {
//			// Blob is small read all its content in one request
//			byte[] content = blob.getContent();
//			writeTo.write(content);
//		} else {
//			// When Blob size is big or unknown use the blob's channel reader.
//			try (ReadChannel reader = blob.reader()) {
//				WritableByteChannel channel = Channels.newChannel(writeTo);
//				ByteBuffer bytes = ByteBuffer.allocate(64 * 1024);
//				while (reader.read(bytes) > 0) {
//					bytes.flip();
//					channel.write(bytes);
//					bytes.clear();
//				}
//			}
//		}
//		if (downloadTo == null) {
//			writeTo.println();
//		} else {
//			writeTo.close();
//		}
//	}
//
//	public void excluir(Storage storage, BlobId... blobIds) {
//		// use batch operation
//		List<Boolean> deleteResults = storage.delete(blobIds);
//		int index = 0;
//		for (Boolean deleted : deleteResults) {
//			if (deleted) {
//				System.out.printf("Blob %s foi deletado%n", blobIds[index]);
//			}
//			index++;
//		}
//	}
//
//	private void run(Storage storage, BlobId blobId, Map<String, String> metadata) {
//		Blob blob = storage.get(blobId);
//		if (blob == null) {
//			System.out.println("No such object");
//			return;
//		}
//		Blob updateBlob = blob.toBuilder().setMetadata(metadata).build().update();
//		System.out.printf("Updated %s%n", updateBlob);
//	}
//
//	public void run(Storage storage, CopyRequest request) {
//		CopyWriter copyWriter = storage.copy(request);
//		System.out.printf("Copied %s%n", copyWriter.getResult());
//	}
//
//	public static void main(String[] args) throws IOException {
//
//		RepositorioCloudGoogleStorage rcgs = new RepositorioCloudGoogleStorage();
//		rcgs.autenticar();
//		rcgs.listarTodos();
//		//https://storage.cloud.google.com/storage-imagem-imob.appspot.com/imovel/1/cloud-app-png-image-68029.png?hl=pt-br&authuser=1&_ga=1.13167284.264594705.1496500017
//		//https://storage.cloud.google.com/storage-imagem-imob.appspot.com/imovel/1_casa_2.jpg?hl=pt-br&authuser=1&_ga=1.46834340.264594705.1496500017
//		
//		String pathimage = "/Users/daniel-matos/Documents/Projetos/workspace-imob/plataforma-imob/plataforma-imob-ejb/src/main/java/com/br4dev/imob/external/repositorio/imp/cloud-app-png-image-68029.png";
//		Path path = Paths.get(pathimage);
//		
//		BlobInfo blobInfo = criarBlobInfo(RepositorioCloudGoogleStorage.BUCKET_NAME_DEFAULT,"imovel","1","cloud-app-png-image-68029.png","image/png");
//		//BlobInfo blobInfo = rcgs.criarBlobInfo(BUCKET_NAME_DEFAULT, "imovel/1/cloud-app-png-image-68029.png", "image/png");
//		rcgs.upload(rcgs.storage_service, path, blobInfo);
//		rcgs.listarTodos();
//	}
}
