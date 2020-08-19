package design_pattern.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Server server = new Server();
        // 将被代理类对象传给代理类
        ProxyServer proxyServer = new ProxyServer(server);
        // 通过代理类对象去执行方法，但是执行的其实是被代理类的方法
        // 即，实现类（被代理类）专心做自己的实现，其他的事情交给代理类去做
        proxyServer.browse();
        // 至此，输出 "真实的服务器访问网络"
    }
}

interface NetWork {
    public void browse();
}

// 被代理类
class Server implements NetWork {
    @Override
    public void browse() {
        System.out.println("真实的服务器访问网络");
    }
}

// 代理类
class ProxyServer implements NetWork {
    private NetWork work;

    public ProxyServer(NetWork work) {
        this.work = work;
    }

    public void check() {
        System.out.println("联网之前的检查工作");
    }

    @Override
    public void browse() {
        check();
        work.browse();
    }
}
