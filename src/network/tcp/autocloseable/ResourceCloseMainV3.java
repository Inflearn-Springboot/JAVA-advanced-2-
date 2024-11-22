package network.tcp.autocloseable;

public class ResourceCloseMainV3 {
    public static void main(String[] args) {
        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외 처리");
            throw new RuntimeException(e);
        } catch (CloselException e) {
            System.out.println("CloselException 예외 처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloselException{
        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;
        try{
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx();
        } catch (CallException e){
            System.out.println("ex : " + e);
            throw e;
        } finally {
            if(resource2 != null){
                try {
                    resource2.closeEx(); // CloseException 발생 -> CallEx 묻혀짐
                } catch(CloselException e) {
                    // close()에서 발생한 예외는 버린다. 필요하면 로깅 정도
                    System.out.println("close ex: " + e);
                }
            }
            if(resource1 != null){
                try {
                    resource1.closeEx(); // 이코드 호출 안됨
                } catch(CloselException e){
                    System.out.println("close ex: " + e);
                }
            }
        }

    }
}
