import { useState, useEffect } from 'react';
import axios from 'axios';

const VOUCHER_API = 'http://localhost:8080/vouchers';
const USER_API = 'http://localhost:8080/users';
const USAGE_API = 'http://localhost:8080/voucher-usages';

function App() {
    const [activeTab, setActiveTab] = useState('dashboard'); 

    // ================== STATE & LOGIC GIỮ NGUYÊN HOÀN TOÀN ==================
    const [vouchers, setVouchers] = useState([]);
    const [users, setUsers] = useState([]);

    const [voucherForm, setVoucherForm] = useState({ code: '', discountPercent: '', quantity: '', expiredDate: '' });
    const [voucherMsg, setVoucherMsg] = useState({ text: '', type: '' });
    const [editingVoucherId, setEditingVoucherId] = useState(null);
    const [searchCode, setSearchCode] = useState('');

    const [userForm, setUserForm] = useState({ fullName: '', email: '', phone: '' });
    const [userMsg, setUserMsg] = useState({ text: '', type: '' });

    const [usageForm, setUsageForm] = useState({ userId: '', voucherId: '' });
    const [usageMsg, setUsageMsg] = useState({ text: '', type: '' });

    useEffect(() => {
        fetchVouchers();
        fetchUsers();
    }, []);

    const fetchVouchers = async () => {
        try {
            const res = await axios.get(VOUCHER_API);
            setVouchers(res.data);
        } catch (error) { console.error(error); }
    };

    const fetchUsers = async () => {
        try {
            const res = await axios.get(USER_API);
            setUsers(res.data);
        } catch (error) { console.error(error); }
    };

    const handleSearchVoucher = async () => {
        if (!searchCode.trim()) {
            fetchVouchers(); return;
        }
        try {
            const res = await axios.get(`${VOUCHER_API}/search?code=${searchCode}`);
            setVouchers(res.data);
        } catch (error) { console.error(error); }
    };

    const handleEditClick = (v) => {
        setEditingVoucherId(v.id);
        setVoucherForm({ code: v.code, discountPercent: v.discountPercent, quantity: v.quantity, expiredDate: v.expiredDate });
        setVoucherMsg({ text: '', type: '' });
    };

    const handleCancelEdit = () => {
        setEditingVoucherId(null);
        setVoucherForm({ code: '', discountPercent: '', quantity: '', expiredDate: '' });
        setVoucherMsg({ text: '', type: '' });
    };

    const handleVoucherSubmit = async (e) => {
        e.preventDefault();
        setVoucherMsg({ text: '', type: '' });
        const payload = { code: voucherForm.code, discountPercent: parseInt(voucherForm.discountPercent), quantity: parseInt(voucherForm.quantity), expiredDate: voucherForm.expiredDate };
        try {
            if (editingVoucherId) {
                await axios.put(`${VOUCHER_API}/${editingVoucherId}`, payload);
                setVoucherMsg({ text: 'Cập nhật thành công!', type: 'success' });
            } else {
                await axios.post(VOUCHER_API, payload);
                setVoucherMsg({ text: 'Tạo thành công!', type: 'success' });
            }
            handleCancelEdit(); fetchVouchers();
        } catch (error) {
            setVoucherMsg({ text: `Lỗi: ${error.response?.data?.error || 'Hệ thống'}`, type: 'danger' });
        }
    };

    const handleDeleteVoucher = async (id) => {
        if (window.confirm('Xóa voucher này?')) {
            await axios.delete(`${VOUCHER_API}/${id}`);
            fetchVouchers();
        }
    };

    const handleUserSubmit = async (e) => {
        e.preventDefault();
        setUserMsg({ text: '', type: '' });
        try {
            await axios.post(USER_API, userForm);
            setUserMsg({ text: 'Thêm user thành công!', type: 'success' });
            setUserForm({ fullName: '', email: '', phone: '' }); fetchUsers();
        } catch (error) {
            setUserMsg({ text: `Lỗi: ${error.response?.data?.error || error.response?.data?.email || 'Hệ thống'}`, type: 'danger' });
        }
    };

    const handleUsageSubmit = async (e) => {
        e.preventDefault();
        setUsageMsg({ text: '', type: '' });
        try {
            const res = await axios.post(USAGE_API, { userId: parseInt(usageForm.userId), voucherId: parseInt(usageForm.voucherId) });
            setUsageMsg({ text: res.data.message || 'Thành công!', type: 'success' });
            setUsageForm({ userId: '', voucherId: '' }); fetchVouchers(); 
        } catch (error) {
            setUsageMsg({ text: `Lỗi: ${error.response?.data?.error || 'Hệ thống'}`, type: 'danger' });
        }
    };

    // ================== GIAO DIỆN MỚI (DASHBOARD LAYOUT) ==================
    return (
        <div className="d-flex" style={{ minHeight: '100vh', backgroundColor: '#f4f6f9' }}>
            
            {/* SIDEBAR BÊN TRÁI */}
            <div className="bg-dark text-white p-3 d-flex flex-column" style={{ width: '260px', position: 'fixed', height: '100vh' }}>
                <div className="text-center mb-4 mt-2">
                    <h3 className="fw-bold text-primary"><i className="bi bi-hexagon-fill me-2"></i>CRM PRO</h3>
                    <small className="text-muted">Quản trị hệ thống</small>
                </div>
                <hr className="bg-secondary" />
                <ul className="nav nav-pills flex-column mb-auto gap-2">
                    <li className="nav-item">
                        <button className={`nav-link text-start w-100 ${activeTab === 'dashboard' ? 'active bg-primary' : 'text-white'}`} onClick={() => setActiveTab('dashboard')}>
                            <i className="bi bi-speedometer2 me-3"></i>Tổng quan
                        </button>
                    </li>
                    <li className="nav-item">
                        <button className={`nav-link text-start w-100 ${activeTab === 'usage' ? 'active bg-primary' : 'text-white'}`} onClick={() => setActiveTab('usage')}>
                            <i className="bi bi-gift me-3"></i>Cấp phát Voucher
                        </button>
                    </li>
                    <li className="nav-item">
                        <button className={`nav-link text-start w-100 ${activeTab === 'voucher' ? 'active bg-primary' : 'text-white'}`} onClick={() => setActiveTab('voucher')}>
                            <i className="bi bi-ticket-perforated me-3"></i>Quản lý Voucher
                        </button>
                    </li>
                    <li className="nav-item">
                        <button className={`nav-link text-start w-100 ${activeTab === 'user' ? 'active bg-primary' : 'text-white'}`} onClick={() => setActiveTab('user')}>
                            <i className="bi bi-people me-3"></i>Quản lý User
                        </button>
                    </li>
                </ul>
                <hr className="bg-secondary" />
                <div className="dropdown">
                    <a href="#" className="d-flex align-items-center text-white text-decoration-none">
                        <img src="https://github.com/mdo.png" alt="" width="32" height="32" className="rounded-circle me-2" />
                        <strong>Admin User</strong>
                    </a>
                </div>
            </div>

            {/* KHU VỰC NỘI DUNG CHÍNH (CONTENT) */}
            <div className="flex-grow-1 p-4" style={{ marginLeft: '260px' }}>
                
                {/* HEADER */}
                <div className="d-flex justify-content-between align-items-center mb-4 bg-white p-3 rounded shadow-sm">
                    <h4 className="mb-0 text-dark fw-bold">
                        {activeTab === 'dashboard' && 'Bảng Điều Khiển'}
                        {activeTab === 'usage' && 'Giao Dịch Cấp Phát Voucher'}
                        {activeTab === 'voucher' && 'Hệ Thống Voucher'}
                        {activeTab === 'user' && 'Cơ Sở Dữ Liệu Khách Hàng'}
                    </h4>
                    <div>
                        <span className="badge bg-success p-2 fs-6"><i className="bi bi-database-check me-1"></i> Database Connected</span>
                    </div>
                </div>

                {/* TAB: TỔNG QUAN (DASHBOARD THỐNG KÊ) */}
                {activeTab === 'dashboard' && (
                    <div className="row g-4">
                        <div className="col-md-4">
                            <div className="card border-0 shadow-sm rounded-4 p-4 text-center bg-primary text-white">
                                <h1 className="display-4 fw-bold"><i className="bi bi-people"></i> {users.length}</h1>
                                <h5>Tổng Khách Hàng</h5>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="card border-0 shadow-sm rounded-4 p-4 text-center bg-warning text-dark">
                                <h1 className="display-4 fw-bold"><i className="bi bi-ticket-detailed"></i> {vouchers.length}</h1>
                                <h5>Mã Khuyến Mãi</h5>
                            </div>
                        </div>
                        <div className="col-md-4">
                            <div className="card border-0 shadow-sm rounded-4 p-4 text-center bg-success text-white">
                                <h1 className="display-4 fw-bold"><i className="bi bi-check-circle"></i> ACTIVE</h1>
                                <h5>Trạng Thái Server</h5>
                            </div>
                        </div>
                    </div>
                )}

                {/* TAB: CẤP PHÁT VOUCHER */}
                {activeTab === 'usage' && (
                    <div className="card border-0 shadow-sm rounded-4 mx-auto" style={{ maxWidth: '600px' }}>
                        <div className="card-header bg-white border-bottom-0 pt-4 pb-0">
                            <h5 className="fw-bold text-center text-primary"><i className="bi bi-gift-fill me-2"></i>Thực Hiện Giao Dịch</h5>
                        </div>
                        <div className="card-body p-4">
                            <form onSubmit={handleUsageSubmit}>
                                <div className="mb-4">
                                    <label className="form-label text-muted fw-bold small">CHỌN KHÁCH HÀNG</label>
                                    <div className="input-group">
                                        <span className="input-group-text bg-light"><i className="bi bi-person"></i></span>
                                        <select className="form-select" value={usageForm.userId} onChange={e => setUsageForm({...usageForm, userId: e.target.value})} required>
                                            <option value="">-- Chọn User từ hệ thống --</option>
                                            {users.map(u => <option key={u.id} value={u.id}>{u.fullName} ({u.email})</option>)}
                                        </select>
                                    </div>
                                </div>
                                <div className="mb-4">
                                    <label className="form-label text-muted fw-bold small">CHỌN VOUCHER</label>
                                    <div className="input-group">
                                        <span className="input-group-text bg-light"><i className="bi bi-ticket"></i></span>
                                        <select className="form-select" value={usageForm.voucherId} onChange={e => setUsageForm({...usageForm, voucherId: e.target.value})} required>
                                            <option value="">-- Chọn Voucher hợp lệ --</option>
                                            {vouchers.map(v => (
                                                <option key={v.id} value={v.id} disabled={v.quantity <= 0 || v.status === 'INACTIVE'}>
                                                    {v.code} - Giảm {v.discountPercent}% (Còn {v.quantity}) {v.quantity <= 0 ? '🚫' : ''}
                                                </option>
                                            ))}
                                        </select>
                                    </div>
                                </div>
                                <button type="submit" className="btn btn-primary w-100 py-2 fw-bold rounded-3">
                                    <i className="bi bi-check2-circle me-2"></i>XÁC NHẬN SỬ DỤNG
                                </button>
                            </form>
                            {usageMsg.text && <div className={`alert alert-${usageMsg.type} mt-4 mb-0 rounded-3`}><i className="bi bi-info-circle me-2"></i>{usageMsg.text}</div>}
                        </div>
                    </div>
                )}

                {/* TAB: QUẢN LÝ VOUCHER */}
                {activeTab === 'voucher' && (
                    <div className="row g-4">
                        <div className="col-md-4">
                            <div className="card border-0 shadow-sm rounded-4">
                                <div className="card-body p-4">
                                    <h5 className="fw-bold mb-4"><i className={`bi ${editingVoucherId ? 'bi-pencil-square text-warning' : 'bi-plus-circle text-primary'} me-2`}></i>{editingVoucherId ? "Sửa Voucher" : "Thêm Voucher"}</h5>
                                    <form onSubmit={handleVoucherSubmit}>
                                        <div className="mb-3">
                                            <input type="text" className="form-control" placeholder="Mã Code" value={voucherForm.code} onChange={e => setVoucherForm({...voucherForm, code: e.target.value})} required />
                                        </div>
                                        <div className="mb-3">
                                            <input type="number" className="form-control" placeholder="Giảm giá (%)" value={voucherForm.discountPercent} onChange={e => setVoucherForm({...voucherForm, discountPercent: e.target.value})} min="1" max="100" required />
                                        </div>
                                        <div className="mb-3">
                                            <input type="number" className="form-control" placeholder="Số lượng" value={voucherForm.quantity} onChange={e => setVoucherForm({...voucherForm, quantity: e.target.value})} min="0" required />
                                        </div>
                                        <div className="mb-4">
                                            <input type="date" className="form-control" value={voucherForm.expiredDate} onChange={e => setVoucherForm({...voucherForm, expiredDate: e.target.value})} required />
                                        </div>
                                        <button type="submit" className={`btn w-100 fw-bold rounded-3 ${editingVoucherId ? 'btn-warning text-dark' : 'btn-primary'}`}>
                                            <i className="bi bi-save me-2"></i>{editingVoucherId ? "LƯU CẬP NHẬT" : "TẠO MỚI"}
                                        </button>
                                        {editingVoucherId && (
                                            <button type="button" onClick={handleCancelEdit} className="btn btn-light border w-100 fw-bold mt-2 rounded-3">HỦY SỬA</button>
                                        )}
                                    </form>
                                    {voucherMsg.text && <div className={`alert alert-${voucherMsg.type} mt-3 mb-0 small`}><i className="bi bi-info-circle me-1"></i>{voucherMsg.text}</div>}
                                </div>
                            </div>
                        </div>
                        <div className="col-md-8">
                            <div className="card border-0 shadow-sm rounded-4">
                                <div className="card-body p-4">
                                    <div className="d-flex justify-content-between align-items-center mb-3">
                                        <h5 className="fw-bold mb-0">Danh sách mã</h5>
                                        <div className="input-group" style={{ width: '250px' }}>
                                            <input type="text" className="form-control form-control-sm" placeholder="Tìm theo code..." value={searchCode} onChange={(e) => setSearchCode(e.target.value)} />
                                            <button onClick={handleSearchVoucher} className="btn btn-outline-secondary btn-sm"><i className="bi bi-search"></i></button>
                                        </div>
                                    </div>
                                    <div className="table-responsive">
                                        <table className="table table-hover align-middle text-center mb-0">
                                            <thead className="table-light text-muted small">
                                                <tr><th>CODE</th><th>GIẢM</th><th>SL</th><th>HẾT HẠN</th><th>TRẠNG THÁI</th><th>THAO TÁC</th></tr>
                                            </thead>
                                            <tbody className="border-top-0">
                                                {vouchers.map(v => (
                                                    <tr key={v.id}>
                                                        <td className="fw-bold text-primary">{v.code}</td>
                                                        <td>{v.discountPercent}%</td>
                                                        <td>{v.quantity}</td>
                                                        <td><span className="text-muted small">{v.expiredDate}</span></td>
                                                        <td><span className={`badge rounded-pill ${v.status === 'ACTIVE' ? 'bg-success' : 'bg-secondary'}`}>{v.status}</span></td>
                                                        <td>
                                                            <button onClick={() => handleEditClick(v)} className="btn btn-sm btn-light text-warning me-1" title="Sửa"><i className="bi bi-pencil"></i></button>
                                                            <button onClick={() => handleDeleteVoucher(v.id)} className="btn btn-sm btn-light text-danger" title="Xóa"><i className="bi bi-trash"></i></button>
                                                        </td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                )}

                {/* TAB: QUẢN LÝ USER */}
                {activeTab === 'user' && (
                    <div className="row g-4">
                        <div className="col-md-4">
                            <div className="card border-0 shadow-sm rounded-4">
                                <div className="card-body p-4">
                                    <h5 className="fw-bold mb-4"><i className="bi bi-person-plus text-success me-2"></i>Thêm Khách Hàng</h5>
                                    <form onSubmit={handleUserSubmit}>
                                        <div className="mb-3">
                                            <input type="text" className="form-control" placeholder="Họ và tên" value={userForm.fullName} onChange={e => setUserForm({...userForm, fullName: e.target.value})} required />
                                        </div>
                                        <div className="mb-3">
                                            <input type="email" className="form-control" placeholder="Email" value={userForm.email} onChange={e => setUserForm({...userForm, email: e.target.value})} required />
                                        </div>
                                        <div className="mb-4">
                                            <input type="text" className="form-control" placeholder="Số điện thoại" value={userForm.phone} onChange={e => setUserForm({...userForm, phone: e.target.value})} />
                                        </div>
                                        <button type="submit" className="btn btn-success w-100 fw-bold rounded-3"><i className="bi bi-person-check me-2"></i>LƯU KHÁCH HÀNG</button>
                                    </form>
                                    {userMsg.text && <div className={`alert alert-${userMsg.type} mt-3 mb-0 small`}><i className="bi bi-info-circle me-1"></i>{userMsg.text}</div>}
                                </div>
                            </div>
                        </div>
                        <div className="col-md-8">
                            <div className="card border-0 shadow-sm rounded-4">
                                <div className="card-body p-4">
                                    <h5 className="fw-bold mb-3">Cơ sở dữ liệu</h5>
                                    <div className="table-responsive">
                                        <table className="table table-hover align-middle mb-0">
                                            <thead className="table-light text-muted small">
                                                <tr><th>ID</th><th>HỌ TÊN</th><th>EMAIL</th><th>ĐIỆN THOẠI</th></tr>
                                            </thead>
                                            <tbody className="border-top-0">
                                                {users.map(u => (
                                                    <tr key={u.id}>
                                                        <td><span className="text-muted">#{u.id}</span></td>
                                                        <td className="fw-bold">
                                                            <div className="d-flex align-items-center">
                                                                <div className="bg-light rounded-circle p-2 me-2"><i className="bi bi-person text-secondary"></i></div>
                                                                {u.fullName}
                                                            </div>
                                                        </td>
                                                        <td><a href={`mailto:${u.email}`} className="text-decoration-none">{u.email}</a></td>
                                                        <td><span className="text-muted small"><i className="bi bi-telephone me-1"></i>{u.phone || 'N/A'}</span></td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </div>
    );
}

export default App;