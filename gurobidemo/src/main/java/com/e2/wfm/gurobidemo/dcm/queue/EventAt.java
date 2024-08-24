package com.e2.wfm.gurobidemo.dcm.queue;

import java.util.Objects;

public class EventAt implements Comparable<EventAt> {
    private final String name;
    private int index;
    
    
    public EventAt(String name, int index) {
        super();
        this.name = name;
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    
    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(EventAt o) {
        int ret = this.index - o.index;
        //System.out.println(this.index + " - " + o.index + " = " + ret);
        return ret;
    }

    @Override
    public String toString() {
        return "EventAt [name=" + name + ", index=" + index + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EventAt other = (EventAt) obj;
        return index == other.index && Objects.equals(name, other.name);
    }
}
