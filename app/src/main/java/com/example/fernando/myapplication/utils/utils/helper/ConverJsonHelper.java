package com.example.fernando.myapplication.utils.utils.helper;

import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 13/11/2017.
 */
public class ConverJsonHelper {

//    public static UserLocal JsonToUser(JSONObject obj) {
//
//        UserLocal user = new UserLocal();
//        SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTER);
//
//        try {
//            user.setIdUser(obj.getLong("id"));
//            if (obj.has("freelancer"))
//                user.setFreelancer(obj.getBoolean("freelancer"));
//            if (obj.has("userFirstName"))
//                user.setUserFirstName(String.valueOf(obj.getString("userFirstName")));
//            if (obj.has("userLastName"))
//                user.setUserLastName(String.valueOf(obj.getString("userLastName")));
//            if (obj.has("userEmail"))
//                user.setUserEmail(String.valueOf(obj.getString("userEmail")));
//            if (obj.has("userDateOfBirth"))
//                user.setUserDateOfBirth(sdf.parse(String.valueOf(obj.getString("userDateOfBirth"))));
//            if (obj.has("userGender"))
//                user.setUserGender(String.valueOf(obj.getString("userGender")));
//            if (obj.has("sinchId"))
//                user.setSinchId(String.valueOf(obj.getString("sinchId")));
//            if (obj.has("userCredits"))
//                user.setUserCredits(obj.getDouble("userCredits"));
//            if (obj.has("avgStars"))
//                user.setAvgStars(obj.getInt("avgStars"));
//
//            if (obj.has("country")) {
//                JSONObject littleChild = obj.getJSONObject("country");
//                CountryLocal country = new CountryLocal();
//                country.setIdCountry(littleChild.getLong("id"));
//                country.setCountryName(String.valueOf(littleChild.getString("countryName")));
//                user.setCountry(country);
//            }
//
//            if (String.valueOf(obj.getString("userBlobPic")) != null) {
//                user.setUserPic(Base64.decode(String.valueOf(obj.getString("userBlobPic")), Base64.DEFAULT));
//            }
//
//            if (user.isFreelancer()) {
//                List<UserServiceTypeLocal> userServiceTypeDTOList = new ArrayList<>();
//                if (obj.has("userServiceTypeList")) {
//                    userServiceTypeDTOList = jsonArrayToListUserServiceType(obj.getJSONArray("userServiceTypeList"));
//                }
//                user.setUserServiceTypeList(userServiceTypeDTOList);
//
//                List<UserSkillLocal> userSkillList = new ArrayList<>();
//                if (obj.has("userSkillList")) {
//                    userSkillList = jsonArrayToListUserSkills(obj.getJSONArray("userSkillList"));
//                }
//                user.setUserSkillList(userSkillList);
//            }
//        } catch (Exception e) {
//            Log.d("FODEU json to user", e.getMessage());
//        }
//        return user;
//    }
}
