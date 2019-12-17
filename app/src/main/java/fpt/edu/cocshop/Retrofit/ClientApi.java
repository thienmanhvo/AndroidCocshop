package fpt.edu.cocshop.Retrofit;


import fpt.edu.cocshop.Service.FBrandService;
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
    public FBrandService fBrandService() {
        return this.getService(FBrandService.class, ConfigApi.BASE_URL);
    }
}

