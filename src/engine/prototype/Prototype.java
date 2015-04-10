package engine.prototype;

import engine.shop.Tag;

/**
 * 
 * @author Tom Puglisi
 *
 */
public interface Prototype<E> {
    public E clone();
    public Tag getTag ();
    public double getRange ();
    
}
