package com.my.food_ordering.service;

import com.my.food_ordering.dto.RestaurantDto;
import com.my.food_ordering.model.Address;
import com.my.food_ordering.model.Restaurant;
import com.my.food_ordering.model.User;
import com.my.food_ordering.repository.AddressRepository;
import com.my.food_ordering.repository.RestaurantRepository;
import com.my.food_ordering.repository.UserRepository;
import com.my.food_ordering.request.CreateRestaurantRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImp implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        Address address=addressRepository.save(req.getAddress());

        Restaurant restaurant=new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) throws Exception {

        Restaurant restaurant=findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null) {
            restaurant.setCuisineType(updateRestaurant.getCuisineType());
        }

        if(restaurant.getDescription()!=null) {
            restaurant.setDescription(updateRestaurant.getDescription());
        }
        if(restaurant.getName()!=null) {
            restaurant.setName(updateRestaurant.getName());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {

        Restaurant restaurant=findRestaurantById(restaurantId);
        //we don't write method fo this because this handle by findRestaurantById "i"f method
        restaurantRepository.delete(restaurant); //automatically throw the exception
    }

    @Override
    public List<Restaurant> getAllRestaurant() {

        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt=restaurantRepository.findById(id);

        if(opt.isEmpty()) {
            throw new Exception("Restaurant not found with id: "+id);
        }
        return opt.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(long userId) throws Exception {
        Restaurant restaurant=restaurantRepository.findByOwnerId(userId);
        if(restaurant==null) {
            throw new Exception("Restaurant not found with owner id: "+userId);
        }
        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant=findRestaurantById(restaurantId);

        RestaurantDto dto=new RestaurantDto();
        dto.setDescription(restaurant.getDescription());
        dto.setImages(restaurant.getImages());
        dto.setTitle(restaurant.getName());
        dto.setId(restaurantId);


        boolean isFavorited= false;
        List<RestaurantDto> favorites = user.getFavoriteitems();
        for(RestaurantDto favorite:favorites) {
            if(favorite.getId().equals(restaurantId)) {
                isFavorited=true;
                break;
            }
        }
        //If the reataurant is alrady favorited, remove it; otherwise ad it to favorites
        if(isFavorited) {
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));  //remove all the restaurant witch are present same id inside the user favorite list.
        } else {
            favorites.add(dto);  //if is not present then i will add.
        }


        userRepository.save(user);

        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant=findRestaurantById(id);
        restaurant.setOpen(restaurant.isOpen());

        return restaurantRepository.save(restaurant);
    }
}
