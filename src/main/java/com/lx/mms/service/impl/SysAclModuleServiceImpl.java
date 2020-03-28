package com.lx.mms.service.impl;

import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysAclModule;
import com.lx.mms.entity.param.AclModuleParam;
import com.lx.mms.exception.ParamException;
import com.lx.mms.mapper.SysAclModuleMapper;
import com.lx.mms.service.SysAclModuleService;
import com.lx.mms.util.BeanValidation;
import com.lx.mms.util.IpUtil;
import com.lx.mms.util.LevelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (SysAclModule)表服务实现类
 *
 * @author Werdio丶
 * @since 2020-03-14 09:56:17
 */
@Service("sysAclModuleService")
@Slf4j
public class SysAclModuleServiceImpl implements SysAclModuleService {
    @Resource
    private SysAclModuleMapper sysAclModuleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysAclModule queryById(Long id) {
        return this.sysAclModuleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysAclModule> queryAllByLimit(int offset, int limit) {
        return this.sysAclModuleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SysAclModule> queryAll(){
        return this.sysAclModuleMapper.queryAll();
    }
    /**
     * 新增数据
     *
     * @param sysAclModule 实例对象
     * @return 实例对象
     */
    @Override
    public SysAclModule insert(SysAclModule sysAclModule) {
        this.sysAclModuleMapper.insert(sysAclModule);
        return sysAclModule;
    }

    /**
     * 修改数据
     *
     * @param aclModuleParam 实例对象
     * @return 实例对象
     */
    @Override
    public int update(AclModuleParam aclModuleParam) {
        // 校验参数
        checkAclModel(aclModuleParam);
        SysAclModule aclModel = buildAclModel(aclModuleParam);

        aclModel.setId(aclModuleParam.getId());
        return sysAclModuleMapper.update(aclModel);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.sysAclModuleMapper.deleteById(id) > 0;
    }

    @Override
    public int save(AclModuleParam aclModuleParam) {
        // 校验参数
        checkAclModel(aclModuleParam);
        SysAclModule aclModule = buildAclModel(aclModuleParam);
        return sysAclModuleMapper.insert(aclModule);
    }

    private SysAclModule buildAclModel(AclModuleParam aclModuleParam) {
        
        SysAclModule aclModule = SysAclModule.builder().name(aclModuleParam.getName()).remark(aclModuleParam.getRemark())
                .parentId(aclModuleParam.getParentId()).seq(aclModuleParam.getSeq()).status(aclModuleParam.getStatus())
                .build();

        // 设置层级
        aclModule.setLevel(LevelUtil.caculatateLevel(getLevel(aclModuleParam.getParentId()), aclModuleParam.getParentId()));

        aclModule.setOperator(RequestHolder.getCurrentUser().getUsername());
        aclModule.setOpetatorIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        aclModule.setOperatorTime(LocalDateTime.now());
        return aclModule;
    }

    // 获取权限的层级
    private String getLevel(Long aclModuleId){

        SysAclModule aclModule = sysAclModuleMapper.queryById(aclModuleId);

        if (aclModule == null){
            return null;
        }

        return aclModule.getLevel();
    }

    private void checkAclModel(AclModuleParam aclModuleParam) {
        // 校验参数
        BeanValidation.check(aclModuleParam);
        // 检查部门是否已经存在
        if (checkExist(aclModuleParam.getParentId(), aclModuleParam.getName(), aclModuleParam.getId())){
            throw new ParamException("同一层级下存在相同的权限");
        }
    }

    private boolean checkExist(Long parentId, String name, Long id) {
        SysAclModule aclModule = sysAclModuleMapper.checkExit(parentId, name, id);
        return aclModule != null;
    }
}