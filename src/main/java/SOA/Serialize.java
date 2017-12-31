package SOA;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.*;
import java.util.Arrays;

public class Serialize {

    public static void main(String[] args) throws Exception {
        Serialize m = new Serialize();
        m.serialize();
        m.hessianSerialize();
    }

    public void serialize() throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(new Demo(1, 2));
        byte[] test = os.toByteArray();
        System.out.println(Arrays.toString(test));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(test);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Demo demo = (Demo) objectInputStream.readObject();
        System.out.println(demo.toString());
    }

    public void hessianSerialize() throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        HessianOutput ho = new HessianOutput(os);
        ho.writeObject(new Demo(1, 2));
        byte[] test = os.toByteArray();
        System.out.println(Arrays.toString(test));

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(test);
        HessianInput in = new HessianInput(byteArrayInputStream);
        Demo demo = (Demo) in.readObject();
        System.out.println(demo.toString());
    }
}

class Demo implements Serializable {
    private int a;
    private int b;

    @Override
    public String toString() {
        return "Demo{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public Demo(int a, int b) {
        this.a = a;
        this.b = b;
    }
}