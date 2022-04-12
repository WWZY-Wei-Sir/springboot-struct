package com.wsir.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wsir.entity.Administrator;
import com.wsir.entity.Card;
import com.wsir.entity.Customer;
import com.wsir.entity.Worker;
import com.wsir.exception.ServiceException;
import com.wsir.service.AdministratorService;
import com.wsir.service.CardService;
import com.wsir.service.CustomerService;
import com.wsir.service.WorkerService;
import com.wsir.service.impl.AdministratorServiceImpl;
import com.wsir.service.impl.CardServiceImpl;
import com.wsir.service.impl.WorkerServiceImpl;
import com.wsir.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器拦截所有的方法，对方法进行验证
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private CardService cardService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private CustomerService customerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        //如果映射不是方法，直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(Constants.CODE_300, "无token，请重新登录");
        }


        String role;
        String userId;
        //获取token中的userId，判断是否合法
        try {
            role = JWT.decode(token).getAudience().get(0);
            userId = JWT.decode(token).getAudience().get(1);
        } catch (JWTDecodeException e) {
            throw new ServiceException(Constants.CODE_300, "token验证失败");
        }



        String password = null;
        //根据role与token的userId查询数据库 是否存在该用户
        if (role.equals(Constants.ROLE_SCHOOL_CARD)) { //用户为校园卡card
            Card card = cardService.selectById(Integer.parseInt(userId));
            if (card == null) {
                throw new ServiceException(Constants.CODE_300, "用户不存在，请重新登录");
            }
            password = card.getCardPwd();
        } else if (role.equals(Constants.ROLE_WORKER)) { //用户为员工worker
            Worker worker = workerService.selectById(Integer.parseInt(userId));
            if (worker == null) {
                throw new ServiceException(Constants.CODE_300, "用户不存在，请重新登录");
            }
            password = worker.getWorkPwd();
        } else if (role.equals(Constants.ROLE_ADMINISTRATOR)) { //用户为管理员administrator
            Administrator administrator = administratorService.selectById(Integer.parseInt(userId));
            if (administrator == null) {
                throw new ServiceException(Constants.CODE_300, "用户不存在，请重新登录");
            }
            password = administrator.getAdministratorPwd();
        } else if (role.equals(Constants.ROLE_CUSTOMER)) { //用户为游客customer
            Customer customer = customerService.selectById(Integer.parseInt(userId));
            if (customerService == null) {
                throw new ServiceException(Constants.CODE_300, "用户不存在，请重新登录");
            }
            password = Constants.ROLE_CUSTOMER;
        } else {
            password = "";
        }

        //用密码sign加签验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(password)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new ServiceException(Constants.CODE_300, "token验证失败，请重新登录");
        }

        return true;
    }

}
