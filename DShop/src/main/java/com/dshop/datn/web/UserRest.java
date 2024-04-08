package com.dshop.datn.web;

import com.dshop.datn.helper.ApiResponse;
import com.dshop.datn.web.dto.request.AddressRequest;
import com.dshop.datn.web.dto.response.AddressResponse;
import com.dshop.datn.web.dto.response.UserResponse;
import com.dshop.datn.mapper.AddressMapper;
import com.dshop.datn.models.Address;
import com.dshop.datn.repositories.AddressRepository;
import com.dshop.datn.services.AddressService;
import com.dshop.datn.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRest {
    private final UserService userService;
    private final AddressService addressService;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public UserRest(UserService userService, AddressService addressService, AddressRepository addressRepository, AddressMapper addressMapper) {
        this.userService = userService;
        this.addressService = addressService;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") long userId){
        try{
            UserResponse userResponse = userService.getUser(userId);
            if (userResponse != null){
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", userResponse), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(ApiResponse.build(400, false, "false", "Không có tài khoản này"), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/address/{id}")
    public ResponseEntity<?> get(@PathVariable("id") long userId){
        try{
            List<AddressResponse> addressResponses = addressService.getAddressByUser(userId);
            if (addressResponses != null){
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", addressResponses), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(ApiResponse.build(201, false, "false", null), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/address/create")
    public ResponseEntity<?> newAddress(@RequestBody AddressRequest addressRequest){
        try{
            if(addressRequest.getUserId() != 0){
                AddressResponse addressResponse = addressService.createAddress(addressRequest);
//              UserResponse userResponse = userService.getUser(addressRequest.getUserId());
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", addressResponse), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(ApiResponse.build(400, false, "false", null), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/address/update/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id") long addressId,
                                           @RequestBody AddressRequest addressRequest){
        try{
            AddressResponse addressResponse = addressService.updateAddress(addressId, addressRequest);
            if (addressResponse != null){
                return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", addressResponse), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(ApiResponse.build(400, false, "false", "Không có tài khoản này"), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/address/default/{id}")
    public ResponseEntity<?> makeDefault(@PathVariable("id") long addressId){
        try{
            Address address = addressRepository.findById(addressId).orElseThrow();
            address.setFocus(1);
            Address newAddress = addressRepository.save(address);
            List<Address> addresses = addressRepository.findByUserId(address.getUser().getId());
            for (Address addr: addresses){
                if(addr.getId() == addressId){
                    break;
                }else {
                    addr.setFocus(0);
                    addressRepository.save(addr);
                }
            }
            return ResponseEntity.ok(addressMapper.mapToResponse(newAddress));
        }catch (Exception e){
            return new ResponseEntity<>("Lỗi!",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/address/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") long addressId) {
        try {
            addressService.deleteAddress(addressId);
            return new ResponseEntity<>(ApiResponse.build(200, true, "Thành công", "Đã xóa địa chỉ thành công"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Lỗi!", HttpStatus.BAD_REQUEST);
        }
    }

}
