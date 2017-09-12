package com.place.eat.resturantsearch.model.mapper;

import com.place.eat.resturantsearch.model.jsonmodel.BaseResponseModel;
import com.place.eat.resturantsearch.model.viewmodel.BaseViewModel;

/**
 * Created by aman on 12/9/17.
 */

public abstract class BaseMapper<T extends BaseViewModel,F extends BaseResponseModel> {

    public abstract T toViewModel(F baseResponseModel);
}
