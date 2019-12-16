package fpt.edu.cocshop.Retrofit;


import fpt.edu.cocshop.Service.FStoreService;

public class ClientApi extends BaseApi {

//    public UserService userService() {
//        return this.getService(FAccountService.class, ConfigApi.BASE_URL);
//    }
//    public FProductService fProductService() {
//        return this.getService(FProductService.class, ConfigApi.BASE_URL);
//    }

    public FStoreService fStoreService() {
        return this.getService(FStoreService.class, ConfigApi.BASE_URL);
    }
//    public FCartService fCartService() {
//        return this.getService(FCartService.class, ConfigApi.BASE_URL);
//    }
}

