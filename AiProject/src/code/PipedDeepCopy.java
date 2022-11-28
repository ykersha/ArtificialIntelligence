package code;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedDeepCopy {
    /**
     * Flag object used internally to indicate that deserialization failed.
     */
    private static final Object ERROR = new Object();

    /**
     * Returns a copy of the object, or null if the object cannot
     * be serialized.
     */
    public static Object copy(Object orig) {
        Object obj = null;
        try {
            // Make a connected pair of piped streams
            PipedInputStream in = new PipedInputStream();
            PipedOutputStream pos = new PipedOutputStream(in);

            // Make a deserializer thread (see inner class below)
            Deserializer des = new Deserializer(in);

            // Write the object to the pipe
            ObjectOutputStream out = new ObjectOutputStream(pos);
            out.writeObject(orig);

            // Wait for the object to be deserialized
            obj = des.getDeserializedObject();

            // See if something went wrong
            if (obj == ERROR)
                obj = null;
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return obj;

    }
}
