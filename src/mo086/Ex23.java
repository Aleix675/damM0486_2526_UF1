package mo086;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore.SecretKeyEntry;

public class Ex23 {

	public static void main(String[] args) {
		int sumaCodi=0;
		char parell;
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("secret.bin"));

			for(int i=0;i<10;i++) {
				int ale=(int) (Math.random()*3)+1;
				sumaCodi+=ale;
				System.out.print(sumaCodi+":");
				StringBuilder secret= new StringBuilder();
				for(int j=0;j<3;j++) {
					int c=(int)(Math.random()*26)+97;
					parell=(char)c;
					secret.append(parell);
					System.out.print(parell);
				
				}
				dos.writeInt(sumaCodi);
				dos.writeChars(secret.toString());
				System.out.println();
			}
			
			dos.close();
		}catch(Exception e){
			e.getStackTrace();
		}

	}
}
