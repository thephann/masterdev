package main.java.Record;

import main.java.Record.PeopleWritable;
import main.java.Record.SalaryWritable;
import org.apache.hadoop.io.GenericWritable;
import org.apache.hadoop.io.Writable;

public class JoinGenericWritable extends GenericWritable {
    private static Class<? extends Writable>[] CLASSES = null;

    static {
        CLASSES = (Class<? extends Writable>[]) new Class[] {
                SalaryWritable.class,
                PeopleWritable.class
        };
    }

    public JoinGenericWritable() {}

    public JoinGenericWritable(Writable instance) {
        set(instance);
    }

    @Override
    protected Class<? extends Writable>[] getTypes() {
        return CLASSES;
    }
}
