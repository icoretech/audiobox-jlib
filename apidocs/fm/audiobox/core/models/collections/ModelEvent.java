package fm.audiobox.core.models.collections;
public class ModelEvent<E>
{
public  ModelEvent(int what, E source, fm.audiobox.core.models.collections.EventedModelList target, fm.audiobox.core.models.Model notifiers) { throw new RuntimeException("Stub!"); }
public  void trigger() { throw new RuntimeException("Stub!"); }
public static final int ADD = 1;
public static final int REMOVE = -1;
public static final int CLEAR = 0;
public int what;
public E source;
public fm.audiobox.core.models.collections.EventedModelList target;
}
