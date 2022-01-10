package io;

import java.io.Serializable;

/**
 * 英雄类型
 * Created by Mo on 2017/5/10.
 */
public class HeroType implements Serializable {
    private int id;//英雄
    private String type;//英雄类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "id=" + id + "," + "type=" + type;
    }

    public HeroType() {

    }

    public HeroType(int id, String type) {
        this.id = id;
        this.type = type;
    }
}
