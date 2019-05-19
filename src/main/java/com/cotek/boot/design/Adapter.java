package com.cotek.boot.design;

/**
 * @author fuzy
 * create time 19-2-21-下午12:48
 */
public class Adapter {

    private Strategy strategy;

    public Adapter(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object strategy = Class.forName("com.cotek.boot.design." + name).newInstance();
        if (strategy instanceof Strategy) {
            this.strategy = (Strategy) strategy;
        } else {
            throw new RuntimeException("not strategy name");
        }
    }

    public String getInfo(Order order) {
        return strategy.handler(order);
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Order order = new Order();
        Adapter adapter;
        adapter = new Adapter("A");
        System.out.println(adapter.getInfo(order));
        adapter = new Adapter("B");
        System.out.println(adapter.getInfo(order));
        adapter = new Adapter("C");
        System.out.println(adapter.getInfo(order));
    }
}

class Order {
}

interface Strategy {
    String handler(Order order);
}

class A implements Strategy {

    @Override
    public String handler(Order order) {
        return "a";
    }
}

class B implements Strategy {

    @Override
    public String handler(Order order) {
        return "b";
    }
}

class C {
}

