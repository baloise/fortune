package org.ajwerner.voronoi;

/**
 * Created by ajwerner on 12/23/13.
 */
public class Event implements Comparable<Event> {
    public final Point p;

    public Event(Point p) {
        this.p = p;
    }

    @Override
    public int compareTo(Event o) {
        return Point.minYOrderedCompareTo(this.p, o.p);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		return true;
	}
    
    
}
